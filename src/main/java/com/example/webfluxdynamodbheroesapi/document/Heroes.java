package com.example.webfluxdynamodbheroesapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.example.webfluxdynamodbheroesapi.constants.HeroesConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

import static com.example.webfluxdynamodbheroesapi.constants.HeroesConstant.HEROES_API_TABLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = HEROES_API_TABLE)
public class Heroes {

	@Id
	@DynamoDBHashKey(attributeName = "id")
	@NotBlank
	private String id;

	@DynamoDBAttribute(attributeName = "name")
	@NotBlank
	private String name;

	@DynamoDBAttribute(attributeName = "universe")
	@NotBlank
	private String universe;

	@DynamoDBAttribute(attributeName = "films")
	private int films;

}

