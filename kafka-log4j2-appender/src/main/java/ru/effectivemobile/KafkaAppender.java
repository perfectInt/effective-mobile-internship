package ru.effectivemobile;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "Kafka", category = "Core", elementType = Appender.ELEMENT_TYPE)
public class KafkaAppender extends AbstractAppender {

    private final Producer<String, String> producer;
    private final String topic;

    protected KafkaAppender(String name, PatternLayout layout, Producer<String, String> producer, String topic) {
        super(name, null, layout, false, null);
        this.producer = producer;
        this.topic = topic;
    }

    @PluginFactory
    public static KafkaAppender createAppender(@PluginAttribute("name") String name,
                                               @PluginAttribute("topic") String topic,
                                               @PluginElement("Layout") PatternLayout layout) {
        if (name == null) {
            LOGGER.error("No name provided for KafkaAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        Producer<String, String> producer = KafkaProducerFactory.createProducer();
        return new KafkaAppender(name, layout, producer, topic);
    }

    @Override
    public void append(LogEvent event) {
        String message = new String(getLayout().toByteArray(event));
        producer.send(new ProducerRecord<>(topic, null, message));
    }
}
