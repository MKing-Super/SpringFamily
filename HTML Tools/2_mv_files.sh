#!/bin/bash

# 放在 Completed 文件夹中
# 192.168.137.251	8022
# bash 2_mv_files.sh 'Dr. House S01'

# param
movie_name="$1"

# 根据情况修正文件夹位置！！！！！！
MOVIES_ABS_PATH="/data/data/com.termux/files/home/storage/shared/Download/Movies_temp/Completed"

if [ -z "$movie_name"]; then
  echo "参数错误"
  exit 1
fi

VTT_PATH="vtt"
cd "$MOVIES_ABS_PATH/$movie_name"
mkdir -p "${VTT_PATH}"

echo ""
echo ""
echo "============================================================"
echo "遍历当前目录下的所有文件夹（处理 MP4、vtt 文件） start >>>>>>>>>"
for dir1 in */; do
  # 移除末尾的斜杠
  dir_name1="${dir1%/}"
  echo "${dir_name1} ------->"
  if [[ "${dir_name1}" == "${VTT_PATH}" ]]; then
    echo "文件夹 ${dir_name1} 跳过"
	echo "${dir_name1} <--------------------------"
  else
  
    cd "$MOVIES_ABS_PATH/$movie_name/$dir_name1"
    echo "$(pwd)"
    # 遍历当前目录下的所有文件
    for file in *; do
      if [ -f "$file" ]; then
	    
        if [[ "$file" == *.mp4* ]]; then
          echo "移动: $file -> ../${dir_name1}.mp4"
	  	mv "${file}" "../${dir_name1}.mp4"
        fi
	    if [[ "$file" == *Chinese* ]]; then
            echo "移动: $file -> ../vtt/${dir_name1}(Chinese).vtt"
	  	  mv "${file}" "../vtt/${dir_name1}(Chinese).vtt"
        fi
	    if [[ "$file" == *Enghlish* ]]; then
            echo "移动: $file -> ../vtt/${dir_name1}(Enghlish).vtt"
	  	  mv "${file}" "../vtt/${dir_name1}(Enghlish).vtt"
        fi
	    if [[ "$file" == *both* ]]; then
            echo "移动: $file -> ../vtt/${dir_name1}(both).vtt"
	  	  mv "${file}" "../vtt/${dir_name1}(both).vtt"
        fi
      fi
    done
	
    cd ..
    echo "${dir_name1} <--------------------------"
    
  fi
  
done
echo "遍历当前目录下的所有文件夹（处理 MP4、vtt 文件） end <<<<<<<<<<<"







