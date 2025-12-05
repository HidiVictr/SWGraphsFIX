# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Added
- **"Rubber Band" Edge Creation**: Visual feedback line follows the mouse cursor when creating an edge, improving precision.
- **Semantic Zoom**: Smooth zooming centered on the mouse cursor using the mouse wheel.
- **Panning**: Canvas panning support using the Middle Mouse Button or 'm' + Left Click Drag.
- **Variable Window Size**: Application automatically adjusts initial window size to 85% of the screen resolution.

### Changed
- **Optimized Rendering Loop**: Replaced continuous repaint loop with an event-driven system, significantly reducing CPU/GPU usage.
- **Code Cleanup**: Removed decompilation artifacts, renamed variables for clarity, and reformatted key files (`GrafoMultiColor.java`, `PanelGrafosDibujo.java`, `MenuPrincipal.java`).

### Fixed
- **Input Responsiveness**: Resolved issues where the program would stop responding to clicks or keyboard input.
- **Undo System (`Ctrl+Z`)**: Fixed `undoStack` logic for consistent behavior and state synchronization.
- **Double Click Detection**: Corrected event handling to prevent erroneous double-click detection.
- **State Consistency**: Ensured graph model and undo history remain in sync to prevent "ghost" elements.
