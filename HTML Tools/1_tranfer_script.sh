#!/bin/bash

# 放在 Completed 文件夹中
# 192.168.137.251	8022
# bash tranfer_script.sh 'Dr. House S01'
# bash temp 'Dr. House S01'

# param
movie_name="$1"

# 根据情况修正文件夹位置！！！！！！
MOVIES_ABS_PATH="/data/data/com.termux/files/home/storage/shared/Download/Movies_temp/Completed"

if [ -z "$movie_name"]; then
  echo "参数错误"
  exit 1
fi

echo "rename start >>>>>>>>>>>"
cd "$MOVIES_ABS_PATH/$movie_name"
echo "$(pwd)"
# echo "$(ls)"

# 遍历当前目录下的所有文件夹
for dir in */; do
  # 移除末尾的斜杠
  dir_name="${dir%/}"
  
  # 提取点号后的数字
  if [[ "$dir_name" =~ \.([0-9]+)$ ]]; then
    new_name="${BASH_REMATCH[1]}"
	
	# 补零
    if [ ${#new_name} -eq 1 ]; then
        new_name="0${new_name}"
    elif [ ${#new_name} -eq 2 ]; then
        new_name="$new_name"
    else
        # 如果超过两位数，保持原样
        new_name="$new_name"
    fi
	new_name="${movie_name}E${new_name}"
	
    
    # 检查新名称是否已存在
    if [ -d "$new_name" ]; then
        echo "  警告: '$new_name' 已存在，跳过"
    else
	  echo "重命名: '$dir_name' -> '$new_name'"
      mv "$dir_name" "$new_name"
    fi
  else
      echo "跳过: '$dir_name' (不匹配模式)"
  fi
done
echo "rename end <<<<<<<<<<"


echo ""
echo ""
echo "============================================================"
echo "遍历当前目录下的所有文件夹（处理 MP4、vtt 文件） start >>>>>>>>>"
for dir1 in */; do
  # 移除末尾的斜杠
  dir_name1="${dir1%/}"
  echo "${dir_name1} ------->"
  cd "$MOVIES_ABS_PATH/$movie_name/$dir_name1"
  echo "$(pwd)"
  
  # 遍历当前目录下的所有文件
  for file in *; do
    if [ -f "$file" ]; then
	  
      if [[ "$file" == *.mp4* ]]; then
        echo "重命名: $file -> ${dir_name1}.mp4"
		mv "${file}" "${dir_name1}.mp4"
      fi
	  if [[ "$file" == *中文* ]]; then
          echo "重命名: $file -> ${dir_name1}(Chinese).vtt"
		  mv "${file}" "${dir_name1}(Chinese).vtt"
      fi
	  if [[ "$file" == *外文* ]]; then
          echo "重命名: $file -> ${dir_name1}(Enghlish).vtt"
		  mv "${file}" "${dir_name1}(Enghlish).vtt"
      fi
	  if [[ "$file" == *双语* ]]; then
          echo "重命名: $file -> ${dir_name1}(both).vtt"
		  mv "${file}" "${dir_name1}(both).vtt"
      fi
    fi
  done

  echo "${dir_name1} <--------------------------"
  cd ..
done
echo "遍历当前目录下的所有文件夹（处理 MP4、vtt 文件） end <<<<<<<<<<<"


echo ""
echo ""
echo "============================================================"
echo "遍历当前目录下的所有文件夹（处理 m3u8 文件） start >>>>>>>>>>>>>>>"
for dir1 in */; do
  # 移除末尾的斜杠
  dir_name1="${dir1%/}"
  
  cd "$MOVIES_ABS_PATH/$movie_name/$dir_name1"
  # echo "$(pwd)"
  
  # 遍历当前目录下的所有文件
  for file in *; do
    if [ -f "$file" ]; then
	  if [[ "$file" == *.m3u8* ]]; then
	    echo "${dir_name1} ------->"
        echo "找到: $file"
		echo "++++++"
		echo "文件夹对应名称：.${file}_0"
		echo "重命名: $file -> ${dir_name1}.m3u8"
		echo "重命名: .${file}_0 -> ${dir_name1}"
		mv "${file}" "${dir_name1}.m3u8"
		mv ".${file}_0" "${dir_name1}"
		
		season_folder="./${dir_name1}"
		sed "s|.*/|${season_folder}/|" "${dir_name1}.m3u8" > "${dir_name1}.m3u8_bak"
		mv "${dir_name1}.m3u8" "${dir_name1}.m3u8_0"
		mv "${dir_name1}.m3u8_bak" "${dir_name1}.m3u8"
		
		echo "${dir_name1} <--------------------------"
      fi
      
    fi
  done

  cd ..
done
echo "遍历当前目录下的所有文件夹（处理 m3u8 文件） end <<<<<<<<<<<<<<<<<<<"







# 使用方法示例：
# 复制
# cp -r "/data/data/com.termux/files/home/storage/shared/Download/Movies_temp/.Waiting/Dr. House S01" "/data/data/com.termux/files/home/storage/shared/Download/Movies_temp/Completed/Dr. House S01"
# 执行
# bash tranfer_script.sh 'Dr. House S01'


