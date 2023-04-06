# diverse rf ting (reseptformidleren)

Bibliotek for genering av meldinger til Reseptformidleren.

Består av:
<!-- Keycloak, en issuer for å teste utsteding av tokens. TODO, lag guide på oppsett og test. -->
- Meldingsdefinisjoner:
  - Kith standarder hentet fra https://sarepta.ehelse.no/
  - cxf-xjc-plugin brukt for å lage javaobjekter av meldingsdefinisjonene
    - Lagt til immutable-xjc-plugin for å lage builders
- message-builder
  - Ment å brukes for å generere objektene som skal sendes.
- soap
  - SOAP webservice Laget med Apache CXF jax-ws https://cxf.apache.org/docs/jax-ws.html
      - Sikret med standard WS-Security https://www.oasis-open.org/committees/download.php/16790/wss-v1.1-spec-os-SOAPMessageSecurity.pdf
      - Tester:
        - Lagt til testcase som viser parsing av SAML token.
        - Lagt til testcase som viser parsing og validering av Bearer token fra trusted issuer.
- rest
  - REST webservice laget med Apache CXF jax-rs https://cxf.apache.org/docs/jax-rs.html
    - Sikret med https://cxf.apache.org/docs/jax-rs-jose.html




