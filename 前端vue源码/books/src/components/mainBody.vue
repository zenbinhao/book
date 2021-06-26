<template>
  <div class="mainBody-box">
  <el-row type="flex">
    <el-col :span="18" :offset="2">
    <el-carousel height="4.6rem" direction="vertical" class="text-lb">
        <el-carousel-item v-for="(obj,index) in titleList" :key="index">
          <div class="slideshow-p">
            <p>{{obj}}</p>
          </div>
        </el-carousel-item>
      </el-carousel>
    </el-col>
  </el-row>
  <el-row type="flex">
    <el-col :span="18" :offset="2">
      <h1 class="title-h-one">推荐书籍</h1>
    </el-col>
  </el-row>
  <el-row type="flex">
    <el-col :span="19" :offset="2">
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="book">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/997e8ba4-d69f-4442-937a-bc76a5668f2b.jpg">
            </div>
            <h2>红楼梦</h2>
            <p>《红楼梦》，中国四大名著之一，作者曹雪芹，清代著名小说，曾经被当作禁书。后世为研究《红楼梦》已经成立专门学会，称之为“红学会”。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(1)" plain>了解详情</el-button>
            </div>
          </div>
          <div class="book theMargin">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/abd67109-584e-423e-b278-549665fc9752.jpg">
            </div>
            <h2>西游记</h2>
            <p>《西游记》是中国古典神魔小说中的巅峰之作，书中讲述了唐僧师徒四人一路降妖伏魔，历经整整九九八十一难取经的故事。作者细腻而深刻地塑造了四个极其经典的形象：慈悲、宽厚但软弱、迂腐的师父唐僧；富有反叛精神、神通广大的孙悟空；自私狡猾、好吃懒做的猪八戒；任劳任怨、忠心耿耿的沙僧……他们怀着不同的目的走上了同一条取经路，面对各种难以想象的妖魔鬼怪、险恶绝境，他们既有矛盾，又有合作与情谊。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(20)" plain>了解详情</el-button>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="book">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/c613c17a-0586-4de2-aea6-8432b2750341.jpg">
            </div>
            <h2>水浒传</h2>
            <p>《水浒传》是一部长篇英雄传奇，是中国古代长篇小说的代表作之一，是以宋江起义故事为线索创作出来的。宋江起义发生在北宋徽宗时期，《宋史》的《徽宗本纪》、《侯蒙传》、《张叔夜传》等都有记载。从南宋起，宋江起义的故事就在民间流传，《醉翁谈录》记载了一些独立的有关水浒英雄的传说，《大宋宣和遗事》把许多水浒故事联缀起来，和长篇小说已经很接近。元代出现了不少水浒戏，一批梁山英雄作为舞台形象出现。《水浒传》是宋江起义故事在民间长期流传基础上产生出来的，吸收了民间文学的营养。 《水浒传》是我国人民最喜爱的古典长篇白话小说之一。它产生于明代，是在宋、元以来有关水浒的故事、话本、戏曲的基础上，由作者加工整理、创作而成的。全书以宋江领导的农民起义为主要题材，艺术地再现了中国古代人民反抗压迫、英勇斗争的悲壮画卷。作品充分暴露了封建统治阶级的腐朽和残暴，揭露了当时尖锐对立的社会矛盾和“官逼民反”的残酷现实，成功地塑造了鲁智深、李逵、武松、林冲、阮小七等一批英雄人物。小说故事情节曲折，语言生动，人物性格鲜明，具有高度的艺术成就。但作品歌颂、美化宋江，鼓吹“忠义”和“替天行道”，表现出严重的思想局限。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(21)" plain>了解详情</el-button>
            </div>
          </div>
          <div class="book theMargin">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/9fbf87f2-98e5-4830-9063-20e317f15c0a.jpg">
            </div>
            <h2>三国演义</h2>
            <p>《三国演义》描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事。反映了三国时代各类社会斗争与矛盾的转化，并概括了这一时代的历史巨变，塑造了一群叱咤风云的三国英雄人物。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(11)" plain>了解详情</el-button>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="book">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/cfab9916-0be4-4dcf-9796-d16eac40014d.jpg">
            </div>
            <h2>穆斯林的葬礼</h2>
            <p>《穆斯林的葬礼》是霍达创作的一部长篇小说，该小说以回族手工匠人梁亦清的玉器作坊奇珍斋升沉起伏为主线，在历史的背景下描写梁家三代人不同的命运变迁，表现了主人公为追求理想和事业，为完善自身素质所发出的蓬勃不息的命运意识。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(22)" plain>了解详情</el-button>
            </div>
          </div>
          <div class="book theMargin">
            <div class="book-img">
              <img src="http://101.132.237.188:8080/book/img/6.jpg">
            </div>
            <h2>朝花夕拾</h2>
            <p>此文集作为“回忆的记事”，多侧面地反映了作者鲁迅青少年时期的生活，形象地反映了他的性格和志趣的形成经过。前七篇反映他童年时代在绍兴的家庭和私塾中的生活情景，后三篇叙述他从家乡到南京，又到日本留学，然后回国教书的经历；揭露了半封建半殖民地社会种种丑恶的不合理现象，同时反映了有抱负的青年知识分子在旧中国茫茫黑夜中，不畏艰险，寻找光明的困难历程，以及抒发了作者对往日亲友、师长的怀念之情 [2]  。</p>
            <div class="book-btn">
              <el-button type="info" class="btn-contro fr" @click="routerToDetails(23)" plain>了解详情</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      titleList: ['万般皆下品，唯有读书高! ——孔子', '没有书籍的屋子，就像没有灵魂的躯体。——西塞罗', '书是世界宝贵财富，是国家和历史的优秀遗产。——梭罗', '书籍是屹立在时间的汪洋大海中的灯塔。——惠普尔']
    }
  },
  components: {
  },
  methods: {
    routerToDetails (id) {
      this.$router.push({name: 'details', query: {id: id}})
    }
  },
  computed: {
  },
  mounted () {
  }
}
</script>

<style lang="scss" scoped="scoped">
.mainBody-box{
  .text-lb{
    position: relative;
    top: -2rem;
    z-index: 0;
    background-color: #fff;
    box-shadow: 1px 1px 5px #eaeaea;
    .slideshow-p{
      width: 100%;
      height: 4.6rem;
      text-align: left;
      vertical-align: middle;
    }
  }
  .slideshow-p p{
    display: inline-block;
    height: 50%;
    width: 70%;
    position: relative;
    left: 8rem;
    top: 1.2rem;
    vertical-align: middle;
    line-height: 2.3rem;
  }
  .title-h-one{
    font-size: 1.8rem;
    font-weight: 500;
    color: #666666;
    margin-bottom: 3rem;
  }
  .list-item{
    margin-right: 2.6rem;
    margin-bottom: 3.6rem;
  }
  .book{
  width: 28rem;
  height: 35rem;
  border: 1px solid #eaeaea;
  text-align: center;
  }
  .book-img{
    width: 70%;
    height: 50%;
    overflow: hidden;
    display: inline-block;
  }
  .book-img img{
    width: 100%;
    height: 100%;
  }
  .book h2{
  margin-top: 1.5rem;
  text-align: left;
  font-size: 1.3rem;
  font-weight: 500;
  padding-left: 2.25rem;
  }
  .book p{
    text-indent: 1rem;
    margin-top: 1.5rem;
    padding: 0rem 2.25rem;
    font-weight: 500;
    text-align: left;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    overflow: hidden;
    color: #999999;
    line-height: 2;
  }
  .book-btn{
    width: 80%;
    height: 10%;
    display: inline-block;
    position: relative;
    top: 2rem;
  }
  .btn-contro{
    width: 8rem;
    height: 3rem;
    font-size: 1.1rem;
  }
  .theMargin{
    margin-top: 4rem;
  }
}
</style>
