package com.am.demo.gameplay;

import com.am.demo.domain.Question;
import com.am.demo.entity.Player;

import java.util.Map;
import java.util.Scanner;

public interface Playable
{
    public void play(Map<Integer, Question> questionMap, Scanner scanner, Player player);
}
