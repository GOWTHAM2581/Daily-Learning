# 🛡️ Spring Boot: JWT (JSON Web Token) Implementation

This guide covers the full implementation of **JWT-based authentication** in Spring Boot, including token generation, request filtering, and secure controller endpoints.

---

## 1. 📦 JWT Dependencies

To use JWT, you need the `jjwt` library. Add these to your `pom.xml`:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
</dependency>
```

---

## 2. 🔑 JWT Utility Class (`JwtUtils.java`)

The utility class handles the logic for creating tokens and validating them.

```java
@Component
public class JwtUtils {
    private final String SECRET = "Gowtham-Token-Security-SECRET-KEY";
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (JwtException exception) {
            return false;
        }
    }
}
```

---

## 3. 🛡️ JWT Filter (`JwtFilter.java`)

This filter intercepts every request to check for a valid `Authorization: Bearer <token>` header.

```java
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtils.validateJwtToken(token)) {
                String email = jwtUtils.extractEmail(token);
                var auth = new UsernamePasswordAuthenticationToken(email, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
```

---

## 4. 🚦 Auth Controller (`AuthController.java`)

Handles user login and registration, issuing a JWT upon successful authentication.

```java
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtils.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
```

---

## 🔄 Workflow Summary Recap

1.  **Token Format**: In the Header, use `Authorization: Bearer <token>`.
2.  **JwtUtils**: Generate, extract, and validate claims.
3.  **JwtFilter**: Intercept requests via `OncePerRequestFilter`.
4.  **Security Config**: Add the filter via `.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)`.
5.  **AuthController**: Authenticate user and return the compact JWT string.
