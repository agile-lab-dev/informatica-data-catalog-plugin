package com.witboost.plugin.informatica.common.model.witboost;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = Tag.Deserializer.class)
public class Tag {
    private String tagFQN;
    private String source;
    private String labelType;
    private String state;

    static class Deserializer extends StdDeserializer<Tag> {
        Deserializer() {
            super(Tag.class);
        }

        @Override
        public Tag deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            Tag tag = new Tag();
            if (node.isTextual()) {
                tag.setTagFQN(node.asText());
                return tag;
            }
            tag.setTagFQN(textOrNull(node, "tagFQN"));
            tag.setSource(textOrNull(node, "source"));
            tag.setLabelType(textOrNull(node, "labelType"));
            tag.setState(textOrNull(node, "state"));
            return tag;
        }

        private static String textOrNull(JsonNode node, String field) {
            JsonNode child = node.get(field);
            return child != null && !child.isNull() ? child.asText() : null;
        }
    }
}
