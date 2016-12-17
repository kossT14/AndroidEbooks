package com.example.kosst.ebooksstore.database;

import android.provider.BaseColumns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface DB
{

	public interface Books extends BaseColumns{
		public static final String TABLE_NAME = "eBooks";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String TITLE = TABLE_NAME + "_" + "title";
		public static final String PRICE = TABLE_NAME + "_" + "price";
		public static final String RATING = TABLE_NAME + "_" + "rating";
		public static final String CATEGORY = TABLE_NAME + "_" + "category";

	}

	public interface Authors extends BaseColumns{
		public static final String TABLE_NAME = "Authors";
		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String SURNAME = TABLE_NAME + "_" + "surname";
		public static final String NAME = TABLE_NAME + "_" + "name";
	}

	public interface BookAuthorLink extends BaseColumns{
		public static final String TABLE_NAME = "BookAuthorLink";
		@ForeignKey( table = Books.TABLE_NAME, column = Books.ID, onDeleteCascade = true )
		public static final String ID = TABLE_NAME + "_" + "idAuthor";
		public static final String ISBN = TABLE_NAME + "_" + "isbn";
	}




	public interface Topic extends BaseColumns
	{
		public static final String TABLE_NAME = "topic";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String TITLE = TABLE_NAME + "_" + "title";
		public static final String DESCRIPTION = TABLE_NAME + "_" + "description";
		public static final String IMAGE_URL = TABLE_NAME + "_" + "image_url";
		public static final String FOLLOWING = TABLE_NAME + "_" + "following";
		public static final String TYPE = TABLE_NAME + "_" + "type";
		public static final String RECOMMENDATION_SCORE = TABLE_NAME + "_" + "recommendation_score";
		public static final String MOMENTUM = TABLE_NAME + "_" + "momentum";

		// extra
		public static final String TRENDING = TABLE_NAME + "_" + "trending";

		// TYPE
		public static interface Type
		{
			String INDUSTRY = "Industry";
			String ECONOMY = "Economy";
			String GLOBAL_ISSUE = "Global Issue";
		}
	}

	public interface KeyIssue extends BaseColumns
	{
		public static final String TABLE_NAME = "keyIssue";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String TITLE = TABLE_NAME + "_" + "title";
		public static final String SUBTITLE = TABLE_NAME + "_" + "subtitle";
		public static final String DESCRIPTION = TABLE_NAME + "_" + "description";
		public static final String THUMBNAIL_URL = TABLE_NAME + "_" + "thumbnail_url";
		public static final String IMAGE_URL = TABLE_NAME + "_" + "image_url";

		// for the batch call in maps
		public static final String RELATES_TO = TABLE_NAME + "_" + "relates_to";
		// extra
		public static final String TOPIC_ID = TABLE_NAME + "_" + "topicId";
	}

	public interface Profile extends BaseColumns
	{
		public static final String TABLE_NAME = "profile";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String FIRST_NAME = TABLE_NAME + "_" + "first_name";
		public static final String LAST_NAME = TABLE_NAME + "_" + "last_name";
		public static final String FULL_NAME = TABLE_NAME + "_" + "full_name";
		public static final String BIO = TABLE_NAME + "_" + "bio";
		public static final String THUMBNAIL_URL = TABLE_NAME + "_" + "thumbnail_url";
		public static final String PHOTO_URL = TABLE_NAME + "_" + "photo_url";
		public static final String PRIMARY_ORGANIZATION = TABLE_NAME + "_" + "primary_organisation";
		public static final String PRIMARY_POSITION = TABLE_NAME + "_" + "primary_position";

		// extra
		public static final String SEARCH_TERM = TABLE_NAME + "_" + "searchTerm";

	}

	public interface TopicExpert extends BaseColumns
	{
		public static final String TABLE_NAME = "topicExpert";

		@ForeignKey( table = Profile.TABLE_NAME, column = Profile.ID, onDeleteCascade = true )
		public static final String EXPERT_ID = TABLE_NAME + "_" + "expertId";
		public static final String TOPIC_ID = TABLE_NAME + "_" + "topicId";
	}

	public interface ExpertFollowedTopics extends BaseColumns
	{
		public static final String TABLE_NAME = "expertFollowedTopics";

		@ForeignKey( table = Profile.TABLE_NAME, column = Profile.ID, onDeleteCascade = true )
		public static final String EXPERT_ID = TABLE_NAME + "_" + "expertId";
		public static final String TOPIC_ID = TABLE_NAME + "_" + "topicId";
	}

	public interface Knowledge extends BaseColumns
	{
		public static final String TABLE_NAME = "knowledge";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String TITLE = TABLE_NAME + "_" + "title";
		public static final String TEXT = TABLE_NAME + "_" + "text";
		public static final String AUTHORITY = TABLE_NAME + "_" + "authority";
		public static final String OVERALL_SCORE = TABLE_NAME + "_" + "overall_score";
		public static final String IMAGE_ATTRIBUTION = TABLE_NAME + "_" + "image_attribution";
		public static final String PICTURE_URL = TABLE_NAME + "_" + "picture_url";
		public static final String HAS_READ = TABLE_NAME + "_" + "has_read";
		public static final String REACTION_TAGLINE = TABLE_NAME + "_" + "reaction_tagline";
		public static final String TOPICS_IN_COMMON = TABLE_NAME + "_" + "topics_in_common";
		public static final String MOMENTUM = TABLE_NAME + "_" + "momentum";
		public static final String PICTURE_SCORE = TABLE_NAME + "_" + "picture_score";
		public static final String VIEWS = TABLE_NAME + "_" + "views";
		public static final String PULL_QUOTE = TABLE_NAME + "_" + "pull_quote";
		public static final String TIME_ADDED = TABLE_NAME + "_" + "time_added";
		public static final String SUMMARY = TABLE_NAME + "_" + "summary";
		public static final String AMPLITUDE = TABLE_NAME + "_" + "amplitude";
		public static final String TIME = TABLE_NAME + "_" + "time";
		public static final String LINK = TABLE_NAME + "_" + "link";
		public static final String RELATES_TO_ID = TABLE_NAME + "_" + "relates_to_id";

		// extra
		public static final String SOURCE_NAME = TABLE_NAME + "_" + "sourceName";
		public static final String SOURCE_LICENSE = TABLE_NAME + "_" + "sourceLicense";
		public static final String TRENDING = TABLE_NAME + "_" + "trending";
		public static final String SEARCH_TERM = TABLE_NAME + "_" + "searchTerm";

		// SOURCE_LICENSE
		public static interface SourceLicense
		{
			String FREE = "Free";
		}
	}

	public interface KnowledgeReaction extends BaseColumns
	{
		public static final String TABLE_NAME = "knowledgeReaction";

		@ForeignKey( table = Knowledge.TABLE_NAME, column = Knowledge.ID, onDeleteCascade = true )
		public static final String KNOWLEDGE_ID = TABLE_NAME + "_" + "knowledgeId";
		public static final String PERSON_ID = TABLE_NAME + "_" + "personId";

		// extra
		public static final String TYPE = TABLE_NAME + "_" + "type";

		// TYPE
		public static interface Type
		{
			String OPPORTUNITY = "opportunity";
			String RISK = "risk";
		}
	}

	public interface KnowledgeTopic extends BaseColumns
	{
		public static final String TABLE_NAME = "knowledgeTopic";

		@ForeignKey( table = Knowledge.TABLE_NAME, column = Knowledge.ID, onDeleteCascade = true )
		public static final String KNOWLEDGE_ID = TABLE_NAME + "_" + "knowledgeId";
		public static final String TOPIC_ID = TABLE_NAME + "_" + "topicId";
	}

	public interface KnowledgeKeyIssue extends BaseColumns
	{
		public static final String TABLE_NAME = "knowledgeKeyIssue";

		@ForeignKey( table = Knowledge.TABLE_NAME, column = Knowledge.ID, onDeleteCascade = true )
		public static final String KNOWLEDGE_ID = TABLE_NAME + "_" + "knowledgeId";
		public static final String KEY_ISSUE_ID = TABLE_NAME + "_" + "keyIssueId";
	}

	// it's a topic but for easier use. Subject of change
	public interface TopicForWheel extends BaseColumns
	{
		public static final String TABLE_NAME = "topicForWheel";

		public static final String ID = TABLE_NAME + "_" + "id";
		public static final String TITLE = TABLE_NAME + "_" + "title";
		public static final String DESCRIPTION = TABLE_NAME + "_" + "description";
		public static final String IMAGE_URL = TABLE_NAME + "_" + "image_url";

		// for the wheel
		public static final String DIMENSION_COUNT = TABLE_NAME + "_" + "dimension_count";
		public static final String RELATES_TO = TABLE_NAME + "_" + "relates_to";
		public static final String NAME = TABLE_NAME + "_" + "name";
		public static final String MOMENTUM = TABLE_NAME + "_" + "momentum";
		public static final String STATUS = TABLE_NAME + "_" + "status";
		public static final String TOPLINK_STATUS = TABLE_NAME + "_" + "toplink_status";
		public static final String TRANSFORMATION_MAP = TABLE_NAME + "_" + "transformation_map";
	}

	@Retention( RetentionPolicy.RUNTIME )
	@Target( ElementType.FIELD )
	public @interface ForeignKey
	{
		String table();

		String column();

		boolean onDeleteCascade() default false;
	}
}
