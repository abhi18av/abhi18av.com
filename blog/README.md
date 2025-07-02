# Blog Template System

A comprehensive blog post creation and management system with templates for both technical and personal writing.

## Overview

This system provides structured templates and automation for creating consistent, high-quality blog posts across different categories and styles.

## Directory Structure

```
blog/
├── templates/
│   ├── technical/           # Technical blog templates
│   │   ├── bug-hunt.md
│   │   ├── rewrote-it-in-x.md
│   │   ├── how-we-built-it.md
│   │   ├── lessons-learned.md
│   │   ├── thoughts-on-trends.md
│   │   ├── non-markety-product-perspective.md
│   │   └── benchmarks-and-test-results.md
│   └── personal/            # Personal blog templates
│       ├── reflections.md
│       ├── journey.md
│       ├── philosophy.md
│       └── walkthrough.md
├── justfile                 # Automation commands
├── README.md               # This file
└── *.md                    # Your blog posts
```

## Available Templates

### Technical Templates

1. **Bug Hunt** (`bug-hunt`): Detective story format for debugging experiences
2. **Rewrote it in X** (`rewrote-it-in-x`): Technology migration and rewrite stories
3. **How We Built It** (`how-we-built-it`): Project architecture and development stories
4. **Lessons Learned** (`lessons-learned`): Post-mortem and experience reports
5. **Thoughts on Trends** (`thoughts-on-trends`): Industry analysis and opinion pieces
6. **Non-Markety Product Perspective** (`non-markety-product-perspective`): Honest product reviews
7. **Benchmarks and Test Results** (`benchmarks-and-test-results`): Performance testing reports

### Personal Templates

1. **Reflections** (`reflections`): Thoughtful personal observations and insights
2. **Journey** (`journey`): Personal growth and learning experiences
3. **Philosophy** (`philosophy`): Worldview and belief system exploration
4. **Walkthrough** (`walkthrough`): Step-by-step tutorial format

## Quick Start

### Prerequisites

- [just](https://github.com/casey/just) command runner installed
- Basic familiarity with Markdown

### Creating Your First Blog Post

1. **See available commands:**
   ```bash
   just
   ```

2. **List available templates:**
   ```bash
   just list-templates
   ```

3. **Create a new technical blog post:**
   ```bash
   just new technical bug-hunt "Fixing the Database Connection Pool Bug"
   ```

4. **Create a new personal blog post:**
   ```bash
   just new personal journey "My Path to Understanding Rust"
   ```

### Quick Creation Commands

For frequently used templates, use these shortcuts:

```bash
# Technical posts
just quick-bug "Title"           # Creates bug-hunt post
just quick-build "Title"         # Creates how-we-built-it post
just quick-lessons "Title"       # Creates lessons-learned post
just quick-trend "Title"         # Creates thoughts-on-trends post

# Personal posts
just quick-reflection "Title"    # Creates reflections post
just quick-journey "Title"       # Creates journey post
just quick-philosophy "Title"    # Creates philosophy post
just quick-walkthrough "Title"   # Creates walkthrough post
```

## Available Commands

### Creation Commands

- `just new <type> <template> <title>` - Create new blog post from template
- `just list-templates` - Show all available templates
- `just show-template <type> <template>` - Preview template content

### Management Commands

- `just list` - List all blog posts
- `just drafts` - List draft posts (with `draft: true`)
- `just published` - List published posts
- `just validate` - Check all templates for syntax errors
- `just clean` - Remove temporary files

### Utility Commands

- `just init` - Initialize blog directory structure
- `just` (no arguments) - Show help and available commands

## Template Features

### Frontmatter Structure

All templates include:
- `slug` - URL-friendly post identifier
- `title` - Post title
- `authors` - Author information
- `tags` - Categorization tags
- `date` - Publication date
- `description` - Post summary

### Placeholder System

Templates use bracket notation for placeholders:
- `[title-specific-placeholder]` - Gets replaced with your title
- `[YYYY-MM-DD]` - Gets replaced with current date
- `[slug-for-url]` - Gets replaced with generated slug
- `[technology]`, `[topic]`, etc. - Manual replacement needed

### Automatic Replacements

When creating a post, the system automatically:
1. Generates a date-prefixed filename
2. Creates a URL-friendly slug
3. Replaces date and title placeholders
4. Applies template-specific title replacements

## Workflow

### 1. Create Post
```bash
just new technical lessons-learned "What I Learned Building a Microservices Architecture"
```

### 2. Edit Content
- Open the generated `.md` file
- Replace remaining placeholders marked with `[brackets]`
- Fill in your content following the template structure
- Add appropriate tags for your content

### 3. Review and Publish
- Use `just drafts` to see draft posts
- Remove `draft: true` from frontmatter when ready to publish
- Use `just published` to confirm publication status

## Customization

### Adding New Templates

1. Create a new `.md` file in the appropriate template directory
2. Include proper frontmatter structure
3. Use placeholder notation for dynamic content
4. Update the justfile if special title replacements are needed

### Modifying Existing Templates

Edit template files directly in `templates/technical/` or `templates/personal/`

### Template Validation

Run `just validate` to check all templates for proper frontmatter syntax.

## Tips for Effective Blog Writing

### Using Technical Templates

- **Bug Hunt**: Focus on the detective story - what clues led you to the solution
- **How We Built It**: Include architecture diagrams and code examples
- **Lessons Learned**: Be honest about what didn't work, not just successes
- **Thoughts on Trends**: Provide balanced analysis, avoid pure hype or dismissal

### Using Personal Templates

- **Reflections**: Include concrete examples and actionable insights
- **Journey**: Show progression and growth over time
- **Philosophy**: Connect personal beliefs to practical applications
- **Walkthrough**: Test your instructions with someone unfamiliar with the topic

### General Writing Tips

1. **Replace all placeholders** - Don't leave `[brackets]` in published posts
2. **Use appropriate tags** - Help readers find related content
3. **Include examples** - Abstract concepts benefit from concrete illustrations
4. **Edit ruthlessly** - Clear, concise writing serves readers better
5. **Consider your audience** - Match complexity to reader expectations

## Integration

This blog system is designed to work with:
- Static site generators (Jekyll, Hugo, Gatsby, etc.)
- Documentation platforms (GitBook, Notion, etc.)
- CMS systems that support Markdown
- Version control workflows

## Troubleshooting

### Common Issues

1. **Template not found**: Check spelling and use `just list-templates`
2. **File already exists**: Choose a different title or delete existing file
3. **Placeholder not replaced**: Check the justfile for template-specific rules
4. **Invalid frontmatter**: Run `just validate` to identify issues

### Getting Help

- Run `just` to see all available commands
- Use `just show-template <type> <template>` to preview templates
- Check this README for workflow guidance

## Contributing

To improve this template system:
1. Test new templates thoroughly
2. Follow existing placeholder conventions
3. Update automation for new template-specific replacements
4. Document any new features in this README

---

Happy writing! 🚀
