package com.example.webfluxdynamodbheroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static com.example.webfluxdynamodbheroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;
import static com.example.webfluxdynamodbheroesapi.constants.HeroesConstant.HEROES_API_TABLE;
import static com.example.webfluxdynamodbheroesapi.constants.HeroesConstant.REGION_DYNAMO;

@Slf4j
public class HeroesTable {

	public static void main(String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
				.build();
		DynamoDB dynamoDB = new DynamoDB(client);
		try {
			log.info("Criando tabela, aguarde...");
			Table table = dynamoDB.createTable(
					HEROES_API_TABLE,
					Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
					Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
					new ProvisionedThroughput(5L, 5L)
			);
			table.waitForActive();
			log.info("Successo " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			log.error("Não foi possível criar a tabela");
			log.error(e.getMessage());
		}
	}

}
