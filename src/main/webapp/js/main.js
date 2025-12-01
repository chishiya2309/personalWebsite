/* Phase 4 & 5 Logic */

document.addEventListener("DOMContentLoaded", () => {
  initTheme();
  initCursor();
  renderWeeklyLabs();
  initContactForm();
  initEasterEgg();
});

// 1. Theme Toggle (Phase 4)
function initTheme() {
  const toggleBtn = document.getElementById("theme-toggle");
  const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;

  // Check local storage or preference
  const currentTheme =
    localStorage.getItem("theme") || (prefersDark ? "dark" : "light");
  document.documentElement.setAttribute("data-theme", currentTheme);
  updateBtnText(toggleBtn, currentTheme);

  toggleBtn.addEventListener("click", () => {
    const targetTheme =
      document.documentElement.getAttribute("data-theme") === "dark"
        ? "light"
        : "dark";
    document.documentElement.setAttribute("data-theme", targetTheme);
    localStorage.setItem("theme", targetTheme);
    updateBtnText(toggleBtn, targetTheme);
  });
}

function updateBtnText(btn, theme) {
  btn.textContent = theme === "dark" ? "Light Mode" : "Dark Mode";
}

// 2. Custom Cursor (Phase 4)
function initCursor() {
  // Disable on mobile
  if (window.matchMedia("(max-width: 768px)").matches) return;

  const cursor = document.querySelector(".cursor");

  document.addEventListener("mousemove", (e) => {
    cursor.style.left = e.clientX + "px";
    cursor.style.top = e.clientY + "px";
  });

  // Hover effects
  const hoverables = document.querySelectorAll(
    "a, button, .project-card, .lab-card"
  );
  hoverables.forEach((el) => {
    el.addEventListener("mouseenter", () => cursor.classList.add("hovered"));
    el.addEventListener("mouseleave", () => cursor.classList.remove("hovered"));
  });
}

// 3. Render Weekly Labs (Phase 5)
async function renderWeeklyLabs() {
  const container = document.getElementById("lab-timeline");
  if (!container) return;

  let projects = [];

  try {
    // Try fetching from backend API
    const response = await fetch("api/projects");
    if (response.ok) {
      const data = await response.json();
      // Convert backend data format to frontend format
      projects = data.map((p) => ({
        ...p,
        // Backend sends "HTML,CSS" string -> Frontend needs ["HTML", "CSS"] array
        tech: p.techStack ? p.techStack.split(",") : [],
        // Format date if needed
        date: p.createdAt,
      }));
    } else {
      throw new Error("API returned error");
    }
  } catch (error) {
    console.log("Backend not connected or failed, using static data:", error);
    // Fallback to static data from data.js
    if (typeof weeklyProjects !== "undefined") {
      projects = weeklyProjects;
    }
  }

  const html = projects
    .map(
      (project) => `
        <div class="timeline-item">
            <span class="week-label">Week ${project.week} - ${
        project.date || ""
      }</span>
            <div class="lab-card">
                <h3>${project.title}</h3>
                <p>${project.description}</p>
                <div class="tech-tags">
                    ${project.tech
                      .map((t) => `<small>#${t.trim()}</small> `)
                      .join("")}
                </div>
                <div class="lab-links">
                    <a href="${project.demoLink}" target="_blank">Demo</a>
                    <a href="${
                      project.repoLink
                    }" target="_blank">Source Code</a>
                </div>
            </div>
        </div>
    `
    )
    .join("");

  container.innerHTML = html;
}

// 4. Contact Form Handling
function initContactForm() {
  const form = document.querySelector("form");
  if (!form) return;

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const btn = form.querySelector("button");
    const originalText = btn.textContent;
    btn.textContent = "Sending...";
    btn.disabled = true;

    // Get data from form
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    try {
      const response = await fetch("api/contact", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      const result = await response.json();

      if (result.success) {
        alert("Message sent successfully! Thank you.");
        form.reset();
      } else {
        alert("Failed to send message: " + result.message);
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Something went wrong. Please try again later.");
    } finally {
      btn.textContent = originalText;
      btn.disabled = false;
    }
  });
}

// 5. Easter Egg (Phase 4)
function initEasterEgg() {
  const logo = document.querySelector(".logo");
  let clickCount = 0;

  logo.addEventListener("click", () => {
    clickCount++;
    if (clickCount === 10) {
      alert("🎉 Boom! Bạn đã tìm thấy Easter Egg! Chúc bạn một ngày tốt lành!");
      clickCount = 0;
      // Bonus: Add confetti effect here if library available
    }
  });
}
