# TODO / Technical Debt

## API Design
- [ ] Use `ResponseEntity<T>` with proper HTTP status codes (201 Created for POST, 204 No Content for DELETE)
- [ ] Add pagination (`Pageable`/`Page<T>`) for `GET /notes` and `GET /users` list endpoints

## Security
- [ ] Replace `@RequestParam String username` with real JWT authentication
- [ ] Add `SecurityFilterChain` config in `SecurityConfig`
- [ ] Restrict `PATCH`/`DELETE` endpoints to the resource owner only (currently relies on client-supplied username)

## Observability
- [ ] Add logging (Slf4j) for key actions and errors in controllers/services

## Documentation
- [ ] Add Swagger/OpenAPI (springdoc-openapi) for interactive API docs