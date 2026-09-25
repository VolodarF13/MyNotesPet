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

## Future / Long-term Ideas
- [ ] Build a frontend on top of the existing REST API — a board of draggable/movable note "windows" (Trello/Notion-style)
- [ ] Start with plain HTML/CSS/JS using `fetch` against `/api/v1/notes` and `/api/v1/users`
- [ ] Later explore rewriting it with React and/or Vue
- [ ] Not using Thymeleaf — server-side rendering doesn't fit drag-and-drop interactivity