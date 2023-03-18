
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: zweng <zweng@student.42.fr>                +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2022/08/19 12:41:50 by zweng             #+#    #+#              #
#    Updated: 2023/03/18 11:01:30 by zweng            ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

# ----- varaibles -----

JC 			= javac
JRE 		= java
NAME 		= avaj-launcher
MAINC		= Main
J_PATH 		= srcs
JC_PATH		= classes
SRS			= sources.txt
MAIN		= srcs.simulator.Simulator

# ---------------- transformation ------------------ #

JFILES      = $(notdir $(foreach D, $(J_PATH), $(wildcard $(D)/*.java)))
JCS_NAME	= $(patsubst %.java, %.class, $(JFILES))

# ----- part automatic -----
SRCS 		= $(addprefix $(C_PATH)/,$(CFILES)) 
JCS 		= $(addprefix $(JC_PATH)/,$(JCS_NAME))

# ----- Colors -----
BLACK		:="\033[1;30m"
RED			:="\033[1;31m"
GREEN		:="\033[1;32m"
PURPLE		:="\033[1;35m"
CYAN		:="\033[1;36m"
WHITE		:="\033[1;37m"
EOC			:="\033[0;0m"
#  # ==================

# ----- part rules -----
all: go

go:
	@find * -name "*.java" > $(SRS)
	@$(JC) @$(SRS)
	@$(JRE) $(MAIN) scenario.txt

build: $(JCS)

run:
	@$(JRE) --class-path $(JC_PATH) $(MAINC)

$(JC_PATH)/%.class:$(J_PATH)/%.java | $(JC_PATH)
	@printf $(GREEN)"compiling %s\n"$(EOC) $@
	@$(JC) -d $(JC_PATH) $<

$(JC_PATH):
	@mkdir $(JC_PATH) 2> /dev/null

clean: 
	@rm -f $(JCS) $(SRS) simulation.txt
	@find * -name "*.class" -exec rm {} \;
	@rm -rf $(JC_PATH) 2> /dev/null
	@printf $(GREEN)"$(NAME) clean\n"$(EOC)

re: fclean all

.PHONY: clean fclean re norme all
