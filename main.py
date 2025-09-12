import tkinter as tk
from tkinter import ttk


def build_ui(root: tk.Tk) -> None:
    root.title("Cross the Road - Joke Generator")
    root.minsize(420, 220)

    container = ttk.Frame(root, padding=16)
    container.pack(fill=tk.BOTH, expand=True)

    # Input label and field
    prompt = ttk.Label(container, text="Enter an object, animal, or person:")
    prompt.pack(anchor="w")

    input_var = tk.StringVar()
    entry = ttk.Entry(container, textvariable=input_var)
    entry.pack(fill=tk.X, pady=(4, 12))
    entry.focus_set()

    # Output area
    result_var = tk.StringVar(value="")
    result = ttk.Label(
        container,
        textvariable=result_var,
        justify=tk.LEFT,
        anchor="w",
        wraplength=380,
    )
    result.pack(fill=tk.BOTH, expand=True, pady=(8, 0))

    # Punchline logic
    punchlines = {
        # Exact example behavior requested by user
        "duck": "it didnt there were too many quacks in the road",
        # A few fun extras
        "chicken": "to prove to the possum it could be done",
        "cow": "it wanted to moooove things along",
        "dog": "the barking was on the other side",
        "cat": "purr-haps it wanted to paws for thought",
        "robot": "it followed the shortest path algorithm",
        "programmer": "the API on the other side had better docs",
        "banana": "it slipped up and split",
        "turtle": "to get to the shell station",
        "fish": "to get to the other tide",
    }

    def generate(*_):
        raw = input_var.get().strip()
        if not raw:
            result_var.set("Please enter something to joke about.")
            return

        key = raw.lower()
        punchline = punchlines.get(
            key,
            "to get to the pun-der side",
        )

        question = f"Why did the {raw} cross the road?"
        result_var.set(f"{question}\n{punchline}")

    # Generate button
    btn = ttk.Button(container, text="Generate Joke", command=generate)
    btn.pack(anchor="e")

    # Hit Enter to generate
    entry.bind("<Return>", generate)


def main() -> None:
    root = tk.Tk()
    build_ui(root)
    root.mainloop()


if __name__ == "__main__":
    main()

