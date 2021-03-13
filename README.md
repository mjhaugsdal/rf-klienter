# mitt nettapotek
test av REST jax-rs med jwt OAuth 2.0

Kith standarder hentet fra https://sarepta.ehelse.no/

jaxb2-maven-plugin brukt for å lage javaobjekter av meldingsdefinisjonene.

REST webservice laget med Apache CXF jax-rs http://cxf.apache.org/docs/jax-rs.html

Swagger annotering av security. Sikret med TLS + parsing av bearer token (JWT), men ikke implementert oppslag eller sjekk av trust (mangler jo auth server)

Alternativer som encrypted payload og xmldsig er kommentert i koden for mulig tilleggssikkerhet.


