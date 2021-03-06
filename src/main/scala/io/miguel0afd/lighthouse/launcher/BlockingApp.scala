/*
 *
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package io.miguel0afd.lighthouse.launcher

import java.io.File
import java.util.Calendar

import org.apache.commons.io.FileUtils
import org.apache.spark.{SparkConf, SparkContext}

object BlockingApp extends App {

  val conf = new SparkConf()
    .setMaster("spark://Miguels-MacBook-Pro.local:7077")
    .setAppName("blocking_app")

  val sc = new SparkContext(conf)

  val blockingTime = 20*1000

  val start = System.currentTimeMillis()

  Thread.sleep(blockingTime)

  val end = System.currentTimeMillis()

  val array = Array(
    s"App: ${sc.applicationId}",
    s"Blocking Time: $blockingTime",
    s"Start: $start",
    s"End: $end")

  val rdd = sc.parallelize(array)

  FileUtils.deleteDirectory(new File("blocking_result"))

  rdd.saveAsTextFile("blocking_result")

  sc.stop()

}
