curl -X GET "http://localhost:8080/realms/BandGBus/protocol/openid-connect/auth" \
-H "x-chenile-tenant-id: BandGBus" \
-d "response_type=code" \
-d "client_id=bg-client" \
-d "redirect_uri=http://localhost:8002/h2-console" \
-d "scope=openid profile"