# MyMind — Architecture Decision Records

## ADR 001: Evolve the existing codebase
**Status:** Accepted

Use the user's fork of MyBrain as the starting point rather than creating a second application from scratch. Preserve the upstream GPL-3.0 obligations and retain attribution, notices, and corresponding source availability as required.

## ADR 002: Offline-first productivity core
**Status:** Accepted

Tasks, notes, calendar, reminders, and dashboard must function without network access. Local persistence remains the source of truth for core user data. Network-dependent AI is optional and must fail gracefully.

## ADR 003: Feature scope
**Status:** Accepted

Keep tasks/subtasks, Markdown notes, calendar/events, reminders/notifications, AI assistant, dashboard/widgets. Remove local device sync, diary/mood tracking, bookmarks, and advanced integrations from the product experience.

Before removing modules, map imports, dependency injection, database entities, migrations, widgets, notification flows, and data export. Prefer staged deprecation and migration over destructive deletion.

## ADR 004: Free and open-source operating model
**Status:** Accepted

Do not introduce ads, subscriptions, mandatory paid APIs, or paid-locked core features. Prefer replaceable local inference and optional user-hosted services. Audit code and model/voice/asset licences separately; open-source application code does not automatically grant rights to model weights or voice assets.

## ADR 005: Character rendering
**Status:** Prototype required

Mira and user-created AI Twins should share a character-rendering and animation architecture. Evaluate Android performance, Compose interoperability, GLB animation support, facial blendshapes, memory use, and accessibility before choosing the production renderer. A static image or amplitude-only mouth movement is not equivalent to a rigged character with phoneme/viseme lip-sync.

## ADR 006: Face and voice consent
**Status:** Accepted

AI Twin face capture supports photo and guided video. Custom voice creation is restricted to the user's own voice or a voice for which explicit permission is documented. Provide disclosure, consent, deletion, secure storage, and separate opt-in for training. Prefer on-device processing where feasible; disclose any user-controlled server processing.

## ADR 007: App identity migration
**Status:** Deferred

Do not change applicationId until install/update strategy, signing key ownership, data migration, and coexistence with the original MyBrain installation are decided. A new applicationId generally installs as a separate app and does not automatically inherit the old app's private data.

## ADR 008: Default assistant voice
**Status:** Partially specified

The product brief names Siya as Mira's default voice. The exact provider, model, voice ID, redistribution rights, and offline availability must be verified before bundling or shipping it.
