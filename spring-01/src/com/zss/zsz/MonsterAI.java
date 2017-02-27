package com.zss.zsz;
/**
 * 怪物AI装饰 
 * @author zss
 *
 */
public class MonsterAI implements IMonsterAI{
	private IMonsterAI ai;

	@Override
	public void attack() {
		ai.attack();
	}

	public IMonsterAI getAi() {
		return ai;
	}

	public void setAi(IMonsterAI ai) {
		this.ai = ai;
	}
	
}
