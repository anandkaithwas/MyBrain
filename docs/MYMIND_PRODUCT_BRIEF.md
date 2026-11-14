# MyMind — Product Brief

## Product direction
MyMind is an Android-first, offline-first personal productivity workspace derived from the MyBrain codebase. It combines personal organisation with a proactive AI assistant.

**Positioning:** Your world, in sync.

## Product principles
- Permanently free to use: no ads, subscriptions, mandatory paid APIs, or paid-locked core features.
- Open-source-first: audit code, model, voice, and asset licences independently before adoption.
- Offline-first: core productivity features remain usable without an account or network.
- Privacy by design: collect the minimum data; make AI processing location and retention explicit.
- Modular AI: local inference by default where practical, with optional user-controlled self-hosted providers for heavier workloads.
- Preserve user data during feature removal and migrations.

## Release 1 scope

### Keep
1. Tasks and subtasks, including priority, due dates, and reminders.
2. Markdown notes.
3. Calendar and events.
4. Reminders and notifications.
5. AI assistant and agent workflows.
6. Dashboard and widgets.

### Remove from product experience
- Local device sync.
- Diary and mood tracking.
- Bookmarks manager.
- Advanced integrations.

Do not delete underlying tables or modules until dependencies, migrations, import/export paths, and data-preservation requirements have been audited. Existing user data must not be silently discarded.

## Experience and visual identity
- App name: MyMind (working name; trademark and store-name clearance remain outstanding).
- Visual direction: Playful Premium Intelligence.
- Light, colourful, polished interface with expressive cards, restrained gradients, subtle motion, and clear hierarchy.
- Palette: background #F6FAF8; primary text #172923; secondary text #52635B; mint #42B995; deep mint #238C70; soft mint #DDF4EB. Supporting accents may include lilac, peach/coral, and sky blue.
- Navigation: Home, Tasks, Notes, Calendar. AI remains prominent and easy to invoke.
- Mira is the default assistant, with persistent but adaptive presence and accessible controls to hide or reduce character motion.

## Mira and AI Twin
- Mira: realistic adult digital-human character; mint blazer, cream trousers, white sneakers; consistent identity and rig across screens.
- Default voice: Siya, subject to confirming the exact licensed provider/model and voice identifier.
- AI Twin: optional user-created realistic character using either a photo or guided video capture, plus a custom voice created only from the user's own voice or explicitly authorised voice.
- Never promise perfect face reconstruction from one photo. Guided multi-angle capture may improve likeness.
- Require explicit consent, clear AI disclosure, secure handling, deletion controls, and separate consent for any training use.
- Keep character identity separate from assistant intelligence, memory, and app permissions.

## Technical direction (subject to prototype validation)
- Android: Kotlin and Jetpack Compose; retain compatible existing architecture where sensible.
- Data: Room/SQLite with explicit migration tests.
- Background work and reminders: existing WorkManager/Alarm Manager paths, tested against current Android restrictions.
- 3D: evaluate Filament and glTF/GLB with a minimal Compose integration spike before committing.
- Speech and local AI: evaluate open-source, Android-compatible runtimes and models; verify every model/voice licence.
- Heavy AI Twin reconstruction may require a user-controlled GPU/server. Do not make a paid hosted service mandatory.

## Non-goals for initial release
- Rebuilding every feature from scratch.
- Removing database structures before understanding migrations.
- Mandatory cloud accounts or hosted AI.
- Shipping face/voice cloning without consent and privacy controls.

## Acceptance principles
- Core tasks, notes, calendar, reminders, and dashboard work offline.
- No excluded feature appears in primary navigation or onboarding.
- Existing data is preserved or migrated with a clear user-controlled export path.
- Build and tests run in GitHub Actions; no release is called stable until CI and device tests pass.
- All third-party code, models, voices, fonts, and visual assets have documented licence review.
