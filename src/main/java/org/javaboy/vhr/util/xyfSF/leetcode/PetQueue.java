package org.javaboy.vhr.util.xyfSF.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class PetQueue {

    public class Pet{
        private String type;
        public Pet(String type){
            this.type=type;
        }
        public String getType(){
            return this.type;
        }
    }

    public class Dog extends Pet{
        public Dog() {
            super("dog");
        }
    }

    public class Cat extends Pet{
        public Cat() {
            super("cat");
        }
    }

    public class PetEnterQueue{

        private Pet pet;
        private Long count;//时间戳

        public PetEnterQueue(Pet pet, Long count){
            this.pet=pet;
            this.count=count;
        }
        public Pet getPet(){
            return this.pet;
        }
        public Long getCount(){
            return this.count;
        }
        public String getEnterPetType(){
            return this.pet.getType();
        }
    }


    public class DogCatQueue{
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;

        private Long count;  // 计数时间戳

        public DogCatQueue(){
            this.dogQ=new LinkedList<PetEnterQueue>();
            this.catQ=new LinkedList<PetEnterQueue>();
            this.count= Long.valueOf(0);
        }
        //add方法：将cat和dog实例放入队列中
        public void add(Pet pet){
            if (pet.getType().equals("dog")){
                this.dogQ.add(new PetEnterQueue(pet,this.count++));
            }else if (pet.getType().equals("cat")){
                this.catQ.add(new PetEnterQueue(pet,this.count++));
            }else {
                throw new RuntimeException("type not in dog or cat");
            }
        }
        //pollAll方法
        public Pet pollAll(){
            //第一种情况：猫狗队列均不为空；第二种情况：猫队列空，狗队列不为空；第三种情况：狗队列空，猫队列不为空；第四种情况：均为空
            if (!this.catQ.isEmpty()&&!this.dogQ.isEmpty()){
                //比较猫狗队列头结点count
                if (this.dogQ.peek().getCount()<this.catQ.peek().getCount()){
                    return this.dogQ.poll().getPet();
                }else{
                    return this.catQ.poll().getPet();
                }
            }else if (!this.dogQ.isEmpty()){
                return this.dogQ.poll().getPet();
            }else if(!this.catQ.isEmpty()){
                return this.catQ.poll().getPet();
            }else {
                throw new RuntimeException("queue is empty");
            }
        }
        //pollDog方法
        public Dog pollDog(){
            if (!this.dogQ.isEmpty()){
                return (Dog)this.dogQ.poll().getPet();
            }else{
                throw new RuntimeException("dogQ is empty");
            }
        }
        //isEmpty方法:检查是否还有猫或狗实例
        public boolean isEmpty(){
//            if (!this.dogQ.isEmpty()&&!this.catQ.isEmpty()){
//                return true;
//            }
            return this.dogQ.isEmpty()&&!this.catQ.isEmpty();
        }
    }

}
