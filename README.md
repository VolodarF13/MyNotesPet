## Known Limitations / Roadmap

This project is a learning exercise and intentionally leaves some production-grade concerns for later:

- **Authentication**: endpoints currently identify the user via a `username` request parameter rather than a real authentication mechanism (JWT). This is a known simplification — see `TODO.md`.
- **HTTP status codes**: responses don't yet use explicit `ResponseEntity` status codes (e.g. 201 for creation).
- **Pagination**: list endpoints return full result sets without pagination.

See [`TODO.md`](./TODO.md) for the full list of planned improvements.