#!/usr/bin/env python
import sys
import logging
from pathlib import Path
import tempfile
import filecmp
import difflib
import emoji
import subprocess

title = "test_code"
source_ext = "clj"
run_command = ["bb", "-i"]

# logger
logger = logging.getLogger(title)
logger.setLevel(logging.DEBUG)
formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
ch = logging.StreamHandler()
ch.setLevel(logging.DEBUG)
ch.setFormatter(formatter)
logger.addHandler(ch)

if __name__ == "__main__":
    logger.info("start")

    prob_url = sys.argv[1]

    inputs_dir = (
        Path("inputs") / Path(prob_url.split("/")[-3]) / Path(prob_url.split("/")[-1])
    )
    outputs_dir = (
        Path("outputs") / Path(prob_url.split("/")[-3]) / Path(prob_url.split("/")[-1])
    )
    source_folder = Path("codes") / Path(prob_url.split("/")[-3])
    source_file = source_folder / Path(prob_url.split("/")[-1] + "." + source_ext)

    for idx, (input_data, output_data) in enumerate(
        zip(inputs_dir.iterdir(), outputs_dir.iterdir())
    ):
        print("Test Case {} :".format(idx), end="")
        with input_data.open() as fr, tempfile.NamedTemporaryFile() as fw, output_data.open() as fro:
            subprocess.run(
                run_command + [str(source_file.absolute())],
                stdin=fr,
                stdout=fw,
                check=True,
            )
            tempfile_name = fw.name

            result = Path(tempfile_name).open().readlines()
            ans = fro.readlines()

            if result != ans:
                diff = difflib.ndiff(result, ans)
                print(
                    "failed",
                    emoji.emojize(":thumbsdown:", language="alias"),
                )
                print("".join(diff), end="")
            else:
                print("passed", emoji.emojize(":+1:", language="alias"))

    logger.info("end")
