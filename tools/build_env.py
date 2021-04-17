#!/usr/bin/env python3
import sys
import requests
from bs4 import BeautifulSoup
from pathlib import Path
import shutil
import logging

title = "build_env"
source_ext = "clj"


# logger
logger = logging.getLogger(title)
logger.setLevel(logging.DEBUG)
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
ch = logging.StreamHandler()
ch.setLevel(logging.DEBUG)
ch.setFormatter(formatter)
logger.addHandler(ch)


source_ext = "clj"
template_file = "tools/templates/temp.clj"

if __name__ == '__main__':
    logger.info("start")

    prob_url = sys.argv[1]
    template_file = sys.argv[2]

    response = requests.get(prob_url)
    response.raise_for_status()
    soup = BeautifulSoup(response.content, 'html.parser')
    examples = [tag.text for tag in soup.find_all("pre")]
    half_examples = examples[:len(examples)//2]
    inputs = half_examples[1::2]
    outputs = half_examples[2::2]

    # inputs
    logger.info("migrate test inputs")
    inputs_dir = Path("inputs") / Path(prob_url.split("/")[-3]) / Path(prob_url.split("/")[-1])
    inputs_dir.mkdir(parents=True, exist_ok=True)

    for idx, input_data in enumerate(inputs):
        input_file =  (inputs_dir / "{}.txt".format(idx))
        input_file.touch(exist_ok=True)
        input_file.write_text(input_data)

    outputs_dir = Path("outputs") / Path(prob_url.split("/")[-3]) / Path(prob_url.split("/")[-1])
    outputs_dir.mkdir(parents=True, exist_ok=True)

    # outputs
    logger.info("migrate test outputs")
    for idx, output_data in enumerate(outputs):
        output_file =  (outputs_dir / "{}.txt".format(idx))
        output_file.touch(exist_ok=True)
        output_file.write_text(output_data)

    # source
    logger.info("migrate source code")
    source_folder =  Path("codes") /  Path(prob_url.split("/")[-3])
    source_folder.mkdir(exist_ok=True)
    source_file = source_folder / Path(prob_url.split("/")[-1] + "." + source_ext)

    try:
        source_file.touch(exist_ok=False)
        shutil.copyfile(template_file, source_file)

    except FileExistsError:
        logger.info("catch file create error {} is already exist".format(source_file))

    logger.info("done")
