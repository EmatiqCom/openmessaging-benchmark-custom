/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openmessaging.benchmark.worker.commands;

import java.util.concurrent.TimeUnit;

import org.HdrHistogram.Histogram;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static io.openmessaging.benchmark.worker.LocalWorker.E2ELatencyMaxHistoValue;
import static io.openmessaging.benchmark.worker.LocalWorker.NonE2ELatencyMaxHistoValue;

public class CumulativeLatencies {

    @JsonIgnore
    public Histogram publishLatency = new Histogram(NonE2ELatencyMaxHistoValue, 5);
    public byte[] publishLatencyBytes;

    @JsonIgnore
    public Histogram publishDelayLatency = new Histogram(NonE2ELatencyMaxHistoValue, 5);
    public byte[] publishDelayLatencyBytes;

    @JsonIgnore
    public Histogram endToEndLatency = new Histogram(E2ELatencyMaxHistoValue, 5);
    public byte[] endToEndLatencyBytes;

    @JsonIgnore
    public Histogram scheduleLatency = new Histogram(NonE2ELatencyMaxHistoValue, 5);
    public byte[] scheduleLatencyBytes;
}
