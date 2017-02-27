package com.zss.zsz;

public class Main {

	public static void main(String[] args) {
		IMonsterAI defaultAi = new DefaultAI();
		// 构建一只释放普通攻击的怪物
		MonsterAI monsterAI = new MonsterAI();
		monsterAI.setAi(defaultAi);
		monsterAI.attack();
		 System.out.println("========================");
		// 构建一只释放 普通攻击后接->A技能  的怪物
		monsterAI = new MonsterAI();
		AI1 ai1 = new AI1();
		ai1.setAi(defaultAi);
		monsterAI.setAi(ai1);
		monsterAI.attack();
        System.out.println("========================");
		// 构建一只释放 普通攻击后接->A技能->B技能  的怪物
		monsterAI = new MonsterAI();
		ai1 = new AI1();
		ai1.setAi(defaultAi);
		AI2 ai2 = new AI2();
		ai2.setAi(ai1);
		monsterAI.setAi(ai2);
		monsterAI.attack();
	}
}
