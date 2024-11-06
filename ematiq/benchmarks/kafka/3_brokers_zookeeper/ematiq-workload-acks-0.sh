#!/usr/bin/env bash

NAME="100_topics_1_partition_1Kb_zookeper_3_brokers_acks_0"

echo "Writing workload files $NAME"

cat > ematiq_workload_$NAME.yaml << EOF
name: $NAME

topics: 100
partitionsPerTopic: 1
messageSize: 1024
payloadFile: "payload/payload-1Kb.data"
subscriptionsPerTopic: 2
consumerPerSubscription: 1
producersPerTopic: 2
producerRate: 500
consumerBacklogSizeGB: 0
testDurationMinutes: 25
warmupDurationMinutes: 5
EOF

cat > ematiq_config_$NAME.yaml << EOF
name: Kafka
driverClass: io.openmessaging.benchmark.driver.kafka.KafkaBenchmarkDriver
# Kafka client-specific configuration
replicationFactor: 3
reset: true

topicConfig: |
commonConfig: |
  bootstrap.servers=10.0.0.85:9092
  request.timeout.ms=120000
producerConfig: |
  acks=0
consumerConfig: |
  auto.offset.reset=earliest
  enable.auto.commit=false
EOF

echo "Starting benchmark $NAME"

sudo bin/benchmark -d ematiq_config_$NAME.yaml --output ematiq_workload_kafka_$NAME.json ematiq_workload_$NAME.yaml

echo "Benchmark complete $NAME"
