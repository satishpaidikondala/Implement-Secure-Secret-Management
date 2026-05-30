#!/bin/sh

until vault status -address=${VAULT_ADDR}; do
  echo "Waiting for Vault..."
  sleep 2
done

vault kv put secret/user-profile-service \
  db.username="sa" \
  db.password="password123" \
  api.signing-key="abcdefghijklmnopqrstuvwxyz123456"

echo "Vault secrets have been populated."
