package dev.gaudnik.blog.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Content {

	protected UUID uuid;

	protected String title;

	protected String content;

	public Content(@NonNull String title, @NonNull String content) {
		this.title = title;
		this.content = content;
	}

}
