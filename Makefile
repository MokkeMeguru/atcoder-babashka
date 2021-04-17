##
# AtCoder Helper
#
# @file
# @version 0.1
PROBLEM = foo
TEMPLATE = tools/templates/temp.clj

env:
	python tools/build_env.py ${PROBLEM} ${TEMPLATE}

test:
	python tools/test_code.py ${PROBLEM} 

# end

