package RPGjobs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RPGjobs extends JavaPlugin implements Listener{

	HashMap<Integer , String> vampiretitlemap = new HashMap<Integer , String>();
	HashMap<Integer , String> gianttitlemap = new HashMap<Integer , String>();
	HashMap<Integer , String> humantitlemap = new HashMap<Integer , String>();
	HashMap<Integer , String> monstertitlemap = new HashMap<Integer , String>();
	

	public void onEnable(){
		 if(!getDataFolder().exists()) {
			    getDataFolder().mkdir();
			  }
		 	  getLogger().info("====================");
			  File fileconfig =new File(getDataFolder(),"config.yml");
			  if (!(fileconfig.exists())) {saveDefaultConfig();}
			  reloadConfig();
			  vampiretitlemap.put(1, getConfig().getString("Title.vampire.1"));
			  vampiretitlemap.put(2, getConfig().getString("Title.vampire.2"));
			  vampiretitlemap.put(3, getConfig().getString("Title.vampire.3"));
			  vampiretitlemap.put(4, getConfig().getString("Title.vampire.4"));
			  vampiretitlemap.put(5, getConfig().getString("Title.vampire.5"));
			  
			  gianttitlemap.put(1, getConfig().getString("Title.giant.1"));
			  gianttitlemap.put(2, getConfig().getString("Title.giant.2"));
			  gianttitlemap.put(3, getConfig().getString("Title.giant.3"));
			  gianttitlemap.put(4, getConfig().getString("Title.giant.4"));
			  gianttitlemap.put(5, getConfig().getString("Title.giant.5"));
			  
			  humantitlemap.put(1, getConfig().getString("Title.human.1"));
			  humantitlemap.put(2, getConfig().getString("Title.human.2"));
			  humantitlemap.put(3, getConfig().getString("Title.human.3"));
			  humantitlemap.put(4, getConfig().getString("Title.human.4"));
			  humantitlemap.put(5, getConfig().getString("Title.human.5"));
			  
			  monstertitlemap.put(1, getConfig().getString("Title.monster.1"));
			  monstertitlemap.put(2, getConfig().getString("Title.monster.2"));
			  monstertitlemap.put(3, getConfig().getString("Title.monster.3"));
			  monstertitlemap.put(4, getConfig().getString("Title.monster.4"));
			  monstertitlemap.put(5, getConfig().getString("Title.monster.5"));
			  getLogger().info("RPGjobs加载默认数据完成");

			  getLogger().info("====================");
			  
		


			  getLogger().info("RPGjobs加载完毕");
			  getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {

	}
	
	public FileConfiguration load(File file){
        if (!(file.exists())) { 
         try   
        {
           file.createNewFile();
          }
         catch(IOException   e)
           {
                 e.printStackTrace();
           }
        }
        return YamlConfiguration.loadConfiguration(file);
	}
	
	public void Save(File f1 ,FileConfiguration f2){
		try
	    {
	      f2.save(f1);
	    }
	    catch (IOException e1)
	    {
	      e1.printStackTrace();
	    }
	}
	
	public boolean onCommand (CommandSender sender,
			Command cmd,String label,String[] args){
		if(sender instanceof Player){
			Player p = (Player)sender;
			File fileplayers=new File(getDataFolder(),"players.yml");
			FileConfiguration   players = load(fileplayers);
			
			if(label.equalsIgnoreCase("jobs")){
				if(args[0].equalsIgnoreCase("choose")){
					if(!players.contains("Players.list."+p.getName()) ){
							if(args[1].equalsIgnoreCase("vampire")){
	
									players.set("Players.list."+p.getName()+".job", "vampire");
									players.set("Players.list."+p.getName()+".level", 0);
									p.setMaxHealth(20);
									
									Save(fileplayers,players);
							}
							if(args[1].equalsIgnoreCase("giant")){
									players.set("Players.list."+p.getName()+".job", "giant");
									players.set("Players.list."+p.getName()+".level", 0);
									p.setMaxHealth(20);
									
									Save(fileplayers,players);
							}
							if(args[1].equalsIgnoreCase("human")){
									players.set("Players.list."+p.getName()+".job", "human");
									players.set("Players.list."+p.getName()+".level", 0);
									p.setMaxHealth(10);
									
									Save(fileplayers,players);
							}
							if(args[1].equalsIgnoreCase("monster")){
									players.set("Players.list."+p.getName()+".job", "monster");
									players.set("Players.list."+p.getName()+".level", 0);
									p.setMaxHealth(20);
									
									Save(fileplayers,players);
							}
						}
					else{
						p.sendMessage("你已经选过氏族了");
					}
				}
				if(args[0].equalsIgnoreCase("get")){
					if ((sender instanceof Player) && sender.hasPermission("RPGjobs.get"))
			          {
			            ItemStack Item = new ItemStack(373, 1, (short)0,(byte)8196);
			            if (args.length == 2) {
			              Item.setAmount(Integer.parseInt(args[1]));
			            }
			            PlayerInventory I = p.getInventory();
			            ItemMeta meta = Item.getItemMeta();
			            meta.setDisplayName("§a氏族邪能");
			            ArrayList<String> lore = new ArrayList();
			            lore.add("§b[§a氏族邪能§b]");
			            lore.add("§b--§c喝了它！§b--");
			            meta.setLore(lore);
			            Item.setItemMeta(meta);
			            I.addItem(new ItemStack[] { Item });
			          }
			          else
			          {
			            sender.sendMessage("[氏族插件] 没有权限！");
			          }
				}
				if(args[0].equalsIgnoreCase("see")){
					int level = players.getInt("Players.list."+p.getName()+".level");
					String job = players.getString("Players.list."+p.getName()+".job");
					p.sendMessage("§a你的氏族：§c"+job);
      				p.sendMessage("§e当前等级： §b" + (level));
      				int temp = level;
      				int i ;
      				for(i=1;temp>=20;i++){
      					temp-=20;
      				}
      				if(job.equals("vampire")){
      					p.sendMessage("§9当前称号： " + vampiretitlemap.get(i));
	      			}
      				if(job.equals("giant")){
      					p.sendMessage("§9当前称号： " + gianttitlemap.get(i));
	      			}
      				if(job.equals("human")){
      					p.sendMessage("§9当前称号： " + humantitlemap.get(i));
	      			}
      				if(job.equals("monster")){
      					p.sendMessage("§9当前称号： " + monstertitlemap.get(i));
	      			}
					
				}
				if(args[0].equalsIgnoreCase("change")){
					if (sender.hasPermission("RPGjobs.change"))
			          {
			            if(args[1].equalsIgnoreCase("vampire")){

							players.set("Players.list."+p.getName()+".job", "vampire");
							players.set("Players.list."+p.getName()+".level", 0);
							p.setMaxHealth(20);
							
							Save(fileplayers,players);
							p.sendMessage("你背叛了原来的氏族!成功转换为vampire！");
						}
						if(args[1].equalsIgnoreCase("giant")){
								players.set("Players.list."+p.getName()+".job", "giant");
								players.set("Players.list."+p.getName()+".level", 0);
								p.setMaxHealth(20);
								
								Save(fileplayers,players);
								p.sendMessage("你背叛了原来的氏族!成功转换为giant！");
						}
						if(args[1].equalsIgnoreCase("human")){
								players.set("Players.list."+p.getName()+".job", "human");
								players.set("Players.list."+p.getName()+".level", 0);
								p.setMaxHealth(10);
								
								Save(fileplayers,players);
								p.sendMessage("你背叛了原来的氏族!成功转换为giant！");
						}
						if(args[1].equalsIgnoreCase("monster")){
								players.set("Players.list."+p.getName()+".job", "monster");
								players.set("Players.list."+p.getName()+".level", 0);
								p.setMaxHealth(20);
								
								Save(fileplayers,players);
								p.sendMessage("你背叛了原来的氏族!成功转换为monster！");
						}
			            
			          }
			          else
			          {
			            sender.sendMessage("[氏族插件] 该命令不能由控制台执行！");
			          }
				}
				if(args[0].equalsIgnoreCase("reload")){	
					 File fileconfig =new File(getDataFolder(),"config.yml");
					  if (!(fileconfig.exists())) {saveDefaultConfig();}
					  reloadConfig();
					  vampiretitlemap.put(1, getConfig().getString("Title.vampire.1"));
					  vampiretitlemap.put(2, getConfig().getString("Title.vampire.2"));
					  vampiretitlemap.put(3, getConfig().getString("Title.vampire.3"));
					  vampiretitlemap.put(4, getConfig().getString("Title.vampire.4"));
					  vampiretitlemap.put(5, getConfig().getString("Title.vampire.5"));
					  
					  gianttitlemap.put(1, getConfig().getString("Title.giant.1"));
					  gianttitlemap.put(2, getConfig().getString("Title.giant.2"));
					  gianttitlemap.put(3, getConfig().getString("Title.giant.3"));
					  gianttitlemap.put(4, getConfig().getString("Title.giant.4"));
					  gianttitlemap.put(5, getConfig().getString("Title.giant.5"));
					  
					  humantitlemap.put(1, getConfig().getString("Title.human.1"));
					  humantitlemap.put(2, getConfig().getString("Title.human.2"));
					  humantitlemap.put(3, getConfig().getString("Title.human.3"));
					  humantitlemap.put(4, getConfig().getString("Title.human.4"));
					  humantitlemap.put(5, getConfig().getString("Title.human.5"));
					  
					  monstertitlemap.put(1, getConfig().getString("Title.monster.1"));
					  monstertitlemap.put(2, getConfig().getString("Title.monster.2"));
					  monstertitlemap.put(3, getConfig().getString("Title.monster.3"));
					  monstertitlemap.put(4, getConfig().getString("Title.monster.4"));
					  monstertitlemap.put(5, getConfig().getString("Title.monster.5"));
				}
				}
			}
		return false;
	}
	
	@EventHandler
	public void levelupListener (PlayerJoinEvent evt) {
		File fileplayers=new File(getDataFolder(),"players.yml");
		FileConfiguration   players = load(fileplayers);

		if(!players.contains("Players.list."+evt.getPlayer().getName())){
			evt.getPlayer().sendMessage("§b你还没有选择氏族！指令/jobs");
		}
		else{
			evt.getPlayer().sendMessage("§b你的氏族是："+players.getString("Players.list."+evt.getPlayer().getName()+".job"));
		}
		
	}
	
	@EventHandler
	public void levelListener (PlayerItemConsumeEvent evt) {
		File fileplayers=new File(getDataFolder(),"players.yml");
		FileConfiguration   players = load(fileplayers);
		Player p = evt.getPlayer();
		ItemStack Item = evt.getItem();
		if (!Item.hasItemMeta()) {
	        return;
	      }
	    if (!Item.getItemMeta().hasLore()) {
	        return;
	      }
	    if (Item.getTypeId() == 373)
	      {
	        if (!Item.hasItemMeta()) {
	          return;
	        }
	        if (!Item.getItemMeta().hasLore()) {
	          return;
	        }
	        ArrayList<String> lore = (ArrayList)Item.getItemMeta().getLore();
	        for (String l : lore) {
	          if (l.equalsIgnoreCase("§b[§a氏族邪能§b]"))
	          {
	        	  if(players.contains("Players.list."+p.getName())){
	        		  
	      			int level = players.getInt("Players.list."+p.getName()+".level");
	      			if(level<100){
		      			players.set("Players.list."+p.getName()+".level", level+1);
		      			Save(fileplayers,players);
		      			String job = players.getString("Players.list."+p.getName()+".job");
		      			if(job.equals("giant")){
		      				p.setMaxHealth(20+(level+1)*2);
		      			}
		      			
		      			p.sendMessage("§a你的氏族等级  + 1");
	      				p.sendMessage("§e当前等级： §b" + (level+1));
	      				int temp = level+1;
	      				int i ;
	      				for(i=1;temp>=20;i++){
	      					temp-=20;
	      				}
	      				if(job.equals("vampire")){
	      					p.sendMessage("§9当前称号： " + vampiretitlemap.get(i));
		      			}
	      				if(job.equals("giant")){
	      					p.sendMessage("§9当前称号： " + gianttitlemap.get(i));
		      			}
	      				if(job.equals("human")){
	      					p.sendMessage("§9当前称号： " + humantitlemap.get(i));
		      			}
	      				if(job.equals("monster")){
	      					p.sendMessage("§9当前称号： " + monstertitlemap.get(i));
		      			}
	      			}
	      			else{
	      				p.sendMessage("你已经满级啦！");
	      			}
      				
	      		}
	           
	          }
	        }
	      }
		
	}
	
	@EventHandler
	public void DamageListener (EntityDamageByEntityEvent evt) {
		if(evt.getDamager() instanceof Player){
			Player p =(Player)evt.getDamager();
			LivingEntity p2 =(LivingEntity)evt.getEntity();
			File fileplayers=new File(getDataFolder(),"players.yml");
			FileConfiguration   players = load(fileplayers);
			if(players.contains("Players.list."+p.getName())){
			   long time = p.getWorld().getTime();
			   long b = new Long(12000);
			   int level = players.getInt("Players.list."+p.getName()+".level");
			   if(time>b){
					if(players.getString("Players.list."+p.getName()+".job").equals("vampire")){
						double php = p.getHealth();
						double pmaxhp = p.getMaxHealth();
						
						double phuixue = p.getHealth()+level*0.1;
						if(phuixue<pmaxhp){
							if(level<=100){
								p.setHealth(phuixue);
							}
							else{
								p.setHealth(php+10);
							}
						}
						else{
							p.setHealth(pmaxhp);
						}
					}
			   }
				
				if(players.getString("Players.list."+p.getName()+".job").equals("giant")){
					int chance = new Random().nextInt(10)+1;
					if(chance==1){
						PotionEffectType pet1 = PotionEffectType.INCREASE_DAMAGE;
						PotionEffect pe1 = new PotionEffect(pet1, 2*level, 1);
						PotionEffectType pet2 = PotionEffectType.SLOW;
						PotionEffect pe2 = new PotionEffect(pet2, 2*level, 1);
						p.addPotionEffect(pe1);
						p.addPotionEffect(pe2);
					}
				}
				
				if(players.getString("Players.list."+p.getName()+".job").equals("monster")){
					int chance = new Random().nextInt(10)+1;
					if(chance==1){
						PotionEffectType pet1 = PotionEffectType.POISON;
						PotionEffect pe1 = new PotionEffect(pet1, 4*level, 1);
						PotionEffectType pet2 = PotionEffectType.WITHER;
						PotionEffect pe2 = new PotionEffect(pet2, 4*level, 1);
						PotionEffectType pet3 = PotionEffectType.WEAKNESS;
						PotionEffect pe3 = new PotionEffect(pet3, 4*level, 1);
						p2.addPotionEffect(pe1);
						p2.addPotionEffect(pe2);
						p.addPotionEffect(pe3);
						int chance2 = new Random().nextInt(4)+1;
						switch(chance2){
						case 1:
							PotionEffectType pet11 = PotionEffectType.BLINDNESS;
							PotionEffect pe11 = new PotionEffect(pet11, 4*level, 1);
							p2.addPotionEffect(pe11);
							break;
						case 2:
							PotionEffectType pet12 = PotionEffectType.WEAKNESS;
							PotionEffect pe12 = new PotionEffect(pet12, 4*level, 1);
							p2.addPotionEffect(pe12);
							break;
						case 3:
							PotionEffectType pet13 = PotionEffectType.CONFUSION;
							PotionEffect pe13 = new PotionEffect(pet13, 4*level, 1);
							p2.addPotionEffect(pe13);
							break;
						case 4:
							PotionEffectType pet14 = PotionEffectType.SLOW;
							PotionEffect pe14 = new PotionEffect(pet14, 4*level, 1);
							p2.addPotionEffect(pe14);
							break;
						}
					}
				}
				
			}
		}
	}
	@EventHandler
	public void InteractListener (PlayerInteractEntityEvent evt) {
		if(evt.getRightClicked() instanceof Player){
			Player p1 = (Player)evt.getPlayer();
			Player p2 = (Player)evt.getRightClicked();
			File fileplayers=new File(getDataFolder(),"players.yml");
			FileConfiguration   players = load(fileplayers);
			if(players.contains("Players.list."+p1.getName())){
				int level = players.getInt("Players.list."+p1.getName()+".level");
				if(players.getString("Players.list."+p1.getName()+".job").equals("human")){
					double p2hp = p2.getHealth();
					double p2maxhp = p2.getMaxHealth();
					double p2huixue = p2hp+1+level*0.05;
					if(p2hp<p2maxhp){
						if(p2huixue<p2maxhp){
							p2.setHealth(p2huixue);
						}
						else{
							p2.setHealth(p2maxhp);
						}
					}
					
					if(p1.getItemInHand().getTypeId()== 288){
						PotionEffectType pet1 = PotionEffectType.INCREASE_DAMAGE;
						PotionEffect pe1 = new PotionEffect(pet1, 2*level, 1);
						p2.addPotionEffect(pe1);
					}
				}
			}
		}
		if(!(evt.getRightClicked() instanceof Player) && evt.getRightClicked() instanceof LivingEntity){
			Player p1 = (Player)evt.getPlayer();
			File fileplayers=new File(getDataFolder(),"players.yml");
			FileConfiguration   players = load(fileplayers);
			if(players.contains("Players.list."+p1.getName())){
				int level = players.getInt("Players.list."+p1.getName()+".level");
				if(players.getString("Players.list."+p1.getName()+".job").equals("human")){
					int chance = new Random().nextInt(100)+1;
					if(chance<=1+level/20){
						LivingEntity le = (LivingEntity)evt.getRightClicked();
						if(le.getHealth()-2*level>=0){
							le.setHealth(le.getHealth()-2*level);
						}
						else{
							le.setHealth(0);
						}
					}
					
				}
			}
			
			
		}
		
	}
	
	
	@EventHandler
	public void RespawnListener (PlayerRespawnEvent evt){
		Player p1 = (Player)evt.getPlayer();
		File fileplayers=new File(getDataFolder(),"players.yml");
		FileConfiguration   players = load(fileplayers);
		if(players.contains("Players.list."+p1.getName())){
			int level = players.getInt("Players.list."+p1.getName()+".level");
			if(players.getString("Players.list."+p1.getName()+".job").equals("human")){
				p1.setMaxHealth(10);
			}
			if(players.getString("Players.list."+p1.getName()+".job").equals("giant")){
				p1.setMaxHealth(20+level*2);;
			}
		}
	}
}
	
	
	

	

