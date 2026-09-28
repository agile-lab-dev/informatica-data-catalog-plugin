# Informatica Data Catalog plugin

## Prerequisites

- Kubernetes 1.23+
- Helm 3
- A registry pull secret when the image is private
- An existing Kubernetes Secret containing the Informatica credentials

The default Secret name is `witboost-addons-secrets`, with keys
`INFORMATICA_DCMP_USERNAME` and `INFORMATICA_DCMP_PASSWORD`. Create it without
committing credentials to the repository:

```shell
kubectl create secret generic witboost-addons-secrets \
  --from-literal=INFORMATICA_DCMP_USERNAME='<username>' \
  --from-literal=INFORMATICA_DCMP_PASSWORD='<password>'
```

## Install

The GitLab pipeline publishes immutable images to `$CI_REGISTRY_IMAGE` using
the Git tag without a leading `v`, or the commit short SHA for untagged builds.
It also updates the `latest` tag.

```shell
helm upgrade --install informatica-data-catalog-plugin ./helm \
  --set image.registry='<registry>/<project>' \
  --set image.tag='<version>'
```

Use `credentials.existingSecret`, `credentials.usernameKey`, and
`credentials.passwordKey` when the cluster uses different Secret names or
keys. Application settings can be supplied through `configOverride` or
`extraEnvVars`.