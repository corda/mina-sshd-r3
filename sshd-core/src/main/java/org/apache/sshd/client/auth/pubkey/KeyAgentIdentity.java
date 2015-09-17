/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sshd.client.auth.pubkey;

import java.security.PublicKey;

import org.apache.sshd.agent.SshAgent;
import org.apache.sshd.common.util.ValidateUtils;

/**
 * Uses an {@link SshAgent} to generate the identity signature
 *
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public class KeyAgentIdentity implements PublicKeyIdentity {
    private final SshAgent agent;
    private final PublicKey key;

    public KeyAgentIdentity(SshAgent agent, PublicKey key) {
        this.agent = ValidateUtils.checkNotNull(agent, "No signing agent");
        this.key = ValidateUtils.checkNotNull(key, "No public key");
    }

    @Override
    public PublicKey getPublicKey() {
        return key;
    }

    @Override
    public byte[] sign(byte[] data) throws Exception {
        return agent.sign(key, data);
    }
}