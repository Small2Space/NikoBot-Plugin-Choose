package com.github.smallru8.plugin.choose;

import java.awt.Color;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.github.smallru8.NikoBot.Core;
import com.github.smallru8.NikoBot.Embed;
import com.github.smallru8.NikoBot.event.Event.MessageEvent;
import com.github.smallru8.NikoBot.plugins.PluginsInterface;

import net.dv8tion.jda.api.entities.Message;

public class Choose implements PluginsInterface{

	private String pluginName = "Choose";
	
	public void onDisable() {
		EventBus.getDefault().unregister(this);
	}

	public void onEnable() {
		EventBus.getDefault().register(this);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageRecved(MessageEvent e) {
		Message msg = e.msg;
		if(Core.pluginsMGR.isAllowedGuild(e.event.getGuild().getId(), pluginName)) {
			if(!msg.getAuthor().isBot()&&msg.getContentRaw().split(" ")[0].equalsIgnoreCase("/choose")) {
				String[] cmd = msg.getContentRaw().split(" ");
				int sel = (((int)(Math.random()*100))%(cmd.length-1))+1;
				Embed.EmbedSender(Color.pink, msg.getChannel(), ":thinking:", cmd[sel]);
			}else if(!msg.getAuthor().isBot()&&msg.getContentRaw().split(" ")[0].equalsIgnoreCase("/dice")) {
				int sel = (((int)(Math.random()*100))%6)+1;
				String emoji = "";
				switch(sel) {
				case 1:
					emoji = ":one:";
					break;
				case 2:
					emoji = ":two:";
					break;
				case 3:
					emoji = ":three:";
					break;
				case 4:
					emoji = ":four:";
					break;
				case 5:
					emoji = ":five:";
					break;
				case 6:
					emoji = ":six:";
					break;
				}
				Embed.EmbedSender(Color.pink, msg.getChannel(),":game_die:", emoji);
			}
		}
	}
	public String pluginsName() {
		return pluginName;
	}

}
