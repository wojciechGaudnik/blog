package dev.gaudnik.blog.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Content {

	protected UUID uuid = UUID.randomUUID();

	protected String title;

	protected String content;

	public Content(@NonNull String title, @NonNull String content) {
		this.title = title;
		this.content = content;
	}
}
