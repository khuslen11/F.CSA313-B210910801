How to view code coverage for MeetingPlanner

There is already a generated HTML coverage report checked into the repository.

Quick start
- Open this file in Finder/Explorer: /Users/khuslen/repos/school/test-and-verify/lab7or8/MeetingPlanner/htmlReport/index.html
- Or, from the project root (this folder), open: htmlReport/index.html
- This is the main entry point. You can navigate into the package edu.sc.csce747.MeetingPlanner and then to individual classes to see line-by-line coverage.

Alternative location
- A duplicate of the HTML report also exists under bin/htmlReport/index.html (historical build output). Prefer htmlReport/ at the project root.

Where are the tests?
- Source: src/test/java/edu/sc/csce747/MeetingPlanner
- Example: CalendarTest.java contains JUnit 5 tests.

Notes on regenerating coverage (optional)
- This project does not use a Gradle/Maven build file in the repository. The existing report was likely generated via an IDE (e.g., IntelliJ IDEA or Eclipse with EclEmma/JaCoCo).
- To regenerate in IntelliJ IDEA: Run tests with coverage (Run > Run 'All Tests' with Coverage). The IDE will produce an updated HTML report you can export to the htmlReport/ folder.
- To regenerate in Eclipse with EclEmma: Right-click the project > Coverage As > JUnit Test, then export the HTML report to htmlReport/.

Troubleshooting
- If links in the report don’t load, ensure you open index.html using a file URL (double-click in your file manager) rather than serving from a different working directory.
- The package link in the root index is: htmlReport/ns-1/index.html, which drills into edu.sc.csce747.MeetingPlanner.
