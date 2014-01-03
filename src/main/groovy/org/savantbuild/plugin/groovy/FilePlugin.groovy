/*
 * Copyright (c) 2014, Inversoft Inc., All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.savantbuild.plugin.groovy

import org.savantbuild.domain.Project
import org.savantbuild.io.Copier
import org.savantbuild.output.Output
import org.savantbuild.util.jar.JarBuilder

import java.nio.file.Path

/**
 * File plugin.
 *
 * @author Brian Pontarelli
 */
class FilePlugin extends Plugin {

  FilePlugin(Project project, Output output) {
    super(project, output)
  }

  def copy(Closure block) {
    Copier copier = new Copier(output, project.directory)
    block.setDelegate(copier)
    block()
    copier.copy()
  }

  def jar(Path file, Closure block) {
    file = file.isAbsolute() ? file : project.directory.resolve(file)
    JarBuilder builder = new JarBuilder(file)
    block.setDelegate(builder)
    block()
    builder.build()
  }
}
