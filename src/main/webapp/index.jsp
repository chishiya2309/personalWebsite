<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en" data-theme="light">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Portfolio | Discipline & Creativity</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <!-- Phase 4: Custom Cursor -->
        <div class="cursor"></div>

        <div class="container">
            <!-- Header -->
            <header>
                <div class="logo">my.space</div>
                <nav>
                    <ul>
                        <li><a href="#about">About</a></li>
                        <li><a href="#portfolio">Work</a></li>
                        <li><a href="#weekly-lab">Lab</a></li>
                        <li><a href="#contact">Contact</a></li>
                        <li><button id="theme-toggle">Dark Mode</button></li>
                    </ul>
                </nav>
            </header>

            <!-- Phase 2: Hero Section -->
            <section id="hero">
                <h1>Solving problems with<br>Code & Discipline.</h1>
                <p class="tagline">Hi, I'm a Web Developer focused on minimalist interfaces and robust backend systems.
                </p>
            </section>

            <!-- About Section -->
            <section id="about">
                <h2>About Me</h2>
                <p style="max-width: 700px;">
                    Tôi là một người đam mê công nghệ, luôn tìm kiếm sự cân bằng giữa tính thẩm mỹ (Minimalist) và logic
                    chặt chẽ (Code thủ).
                    Với tư duy "Kỷ luật tạo nên sức mạnh", tôi cam kết hoàn thành mọi dòng code với chất lượng cao nhất.
                </p>
            </section>

            <!-- Main Portfolio (Static for now, can be dynamic later) -->
            <section id="portfolio">
                <h2>Selected Work</h2>
                <div class="project-grid">
                    <div class="project-card">
                        <h3>E-Commerce Platform</h3>
                        <p>A full-stack shopping solution built with JSP/Servlet and PostgreSQL.</p>
                    </div>
                    <div class="project-card">
                        <h3>Task Manager</h3>
                        <p>Productivity app focused on getting things done efficiently.</p>
                    </div>
                    <div class="project-card">
                        <h3>Blog Engine</h3>
                        <p>Content management system built from scratch.</p>
                    </div>
                </div>
            </section>

            <!-- Phase 5: Weekly Lab (Rendered via JS) -->
            <section id="weekly-lab">
                <h2>Weekly Playground</h2>
                <p style="margin-bottom: 2rem; color: var(--secondary-text)">Hành trình luyện tập mỗi tuần.</p>

                <!-- Container for Timeline -->
                <div id="lab-timeline" class="timeline">
                    <!-- JS will inject items here -->
                </div>
            </section>

            <!-- Contact -->
            <section id="contact">
                <h2>Get in Touch</h2>
                <form action="#" method="post">
                    <input type="text" name="name" placeholder="Your Name" required>
                    <input type="email" name="email" placeholder="Your Email" required>
                    <textarea name="message" rows="5" placeholder="Your Message"></textarea>
                    <button type="submit">Send Message</button>
                </form>
            </section>

            <footer>
                <p>&copy; 2025 My Portfolio. Built with Discipline.</p>
            </footer>
        </div>

        <!-- Scripts -->
        <script src="js/data.js"></script>
        <script src="js/main.js"></script>
    </body>

    </html>