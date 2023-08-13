Android Development v3.0

# 1. git init: Initializes a new Git repository in the current directory.
			git init
# 2. git add: Adds files to the staging area to be included in the next commit.
			git add <file_name>
			git add .   # Adds all modified files
# 3. git commit: Commits the changes from the staging area to the repository.
			git commit -m "Commit message"
# 4. git push: Pushes your committed changes to a remote repository.
			git push origin <branch_name>
# 5. git status: Shows the status of your working directory and staging area.
			git status
# 6. git branch: Lists all branches in the repository.
			git branch
# 7. git checkout: Switches to a different branch.
			git checkout <branch_name>
			git checkout -b <branch_name>

# 8. git pull: Fetches and merges changes from a remote repository to your local branch.
			git pull origin <branch_name>

- **Fetch the Latest Changes from GitHub:**
  > 			git fetch origin <branch_name>

- **Checkout the Remote Branch:**
  >			git checkout -b <branch_name> origin/<branch_name>

- **Discard Local Changes (Optional):**
  >			git reset --hard origin/<branch_name>

# 9. git log: Shows the commit history.
			git log
# 10. git diff: Shows the differences between your working directory and the repository.
			git diff