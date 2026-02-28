# 📝 Daily Learning: Note-Making Guide

This guide ensures that all daily learning markdown files remain clean, consistent, and highly readable for future reference.

---

## 1. File Structure 🏗️
Each `.md` file should follow this standard hierarchy:
1.  **Title**: Use a single `H1` at the top with a relevant emoji (e.g., `# 🐳 Docker`).
2.  **Introduction**: A brief 1-2 sentence overview of the topic.
3.  **H2 Sections**: Use `##` for major sections.
4.  **H3 Sub-sections**: Use `###` for detailed breakdowns.
5.  **Divider**: Use `---` between major sections for visual clarity.
6.  **Summary/Conclusion**: A quick wrap-up or next steps.

---

## 2. Visual Elements 🎨
| Element | Usage | Markdown Example |
| :--- | :--- | :--- |
| **Emojis** | For headers and key concepts. | `## 🏎️ Efficiency` |
| **Tables** | For comparisons (e.g., VM vs. Docker). | Use `| col | col |` |
| **Bold/Italic** | To highlight core terminology. | `**Immutable** Unit` |
| **Admonitions** | For warnings or tips. | `> [!NOTE]` or `> [!TIP]` |

---

## 3. Code Blocks 🖥️
- Always include the language identifier for syntax highlighting (e.g., `bash`, `javascript`, `dockerfile`).
- Add comments explaining *why* a particular line exists.
- For multi-file examples, include a "Project Structure" tree using `text`.

Example:
```dockerfile
# Use a lightweight base image
FROM node:18-alpine
WORKDIR /app
COPY . .
CMD ["node", "app.js"]
```

---

## 4. Key Rules for Clarity 📏
- **Bullet Points**: Use them often to break up long paragraphs.
- **Short Sentences**: Avoid "wall of text" - keep it punchy.
- **Problem Statement**: Start new topics with a "Why do we need this?" section.
- **Analogies**: Use simple real-world comparisons (e.g., Image = Recipe).
- **Consistency**: Use the same formatting for commands (`bash`) and environment variables (`CODE_STYLE`).

---

## 5. Workflow Summary Recap 🔄
Always include a "Workflow Summary" or "Cheat Sheet" section near the end for quick reference during future coding sessions.

---

## Next Steps for the LLM 🤖
When this `notes-skill.md` is referenced:
1.  Read the raw notes provided.
2.  Identify core concepts, examples, and technical details.
3.  Rewrite and reorganize following this guide's structure.
4.  Optimize for high-level technical clarity and professional presentation.
