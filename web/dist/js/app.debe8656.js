(function(t){function e(e){for(var r,i,o=e[0],c=e[1],u=e[2],l=0,d=[];l<o.length;l++)i=o[l],n[i]&&d.push(n[i][0]),n[i]=0;for(r in c)Object.prototype.hasOwnProperty.call(c,r)&&(t[r]=c[r]);f&&f(e);while(d.length)d.shift()();return s.push.apply(s,u||[]),a()}function a(){for(var t,e=0;e<s.length;e++){for(var a=s[e],r=!0,i=1;i<a.length;i++){var c=a[i];0!==n[c]&&(r=!1)}r&&(s.splice(e--,1),t=o(o.s=a[0]))}return t}var r={},n={app:0},s=[];function i(t){return o.p+"js/"+({about:"about"}[t]||t)+"."+{about:"048651d7"}[t]+".js"}function o(e){if(r[e])return r[e].exports;var a=r[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,o),a.l=!0,a.exports}o.e=function(t){var e=[],a=n[t];if(0!==a)if(a)e.push(a[2]);else{var r=new Promise(function(e,r){a=n[t]=[e,r]});e.push(a[2]=r);var s,c=document.createElement("script");c.charset="utf-8",c.timeout=120,o.nc&&c.setAttribute("nonce",o.nc),c.src=i(t),s=function(e){c.onerror=c.onload=null,clearTimeout(u);var a=n[t];if(0!==a){if(a){var r=e&&("load"===e.type?"missing":e.type),s=e&&e.target&&e.target.src,i=new Error("Loading chunk "+t+" failed.\n("+r+": "+s+")");i.type=r,i.request=s,a[1](i)}n[t]=void 0}};var u=setTimeout(function(){s({type:"timeout",target:c})},12e4);c.onerror=c.onload=s,document.head.appendChild(c)}return Promise.all(e)},o.m=t,o.c=r,o.d=function(t,e,a){o.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},o.t=function(t,e){if(1&e&&(t=o(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(o.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)o.d(a,r,function(e){return t[e]}.bind(null,r));return a},o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,"a",e),e},o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},o.p="/",o.oe=function(t){throw console.error(t),t};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],u=c.push.bind(c);c.push=e,c=c.slice();for(var l=0;l<c.length;l++)e(c[l]);var f=u;s.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"034f":function(t,e,a){"use strict";var r=a("64a9"),n=a.n(r);n.a},"18a8":function(t,e,a){},"2c9a":function(t,e,a){"use strict";var r=a("4dc2"),n=a.n(r);n.a},"2ec4":function(t,e,a){},"4dc2":function(t,e,a){},"501f":function(t,e,a){"use strict";var r=a("fd48"),n=a.n(r);n.a},"56d7":function(t,e,a){"use strict";a.r(e);a("cadf"),a("551c"),a("f751"),a("097d");var r=a("2b0e"),n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("navbar"),a("div",{attrs:{id:"mainSection"}},[a("router-view")],1),a("myFooter")],1)},s=[],i=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"nav-bar-wrapper"},[r("b-navbar",{attrs:{toggleable:"lg",type:"dark"}},[r("b-navbar-brand",{attrs:{href:"/"}},[r("img",{staticStyle:{width:"50px"},attrs:{src:a("cf05")}})]),r("b-navbar-toggle",{attrs:{target:"nav-collapse"}}),r("b-collapse",{attrs:{id:"nav-collapse","is-nav":""}},[r("b-navbar-nav",[r("b-nav-item",{attrs:{href:"#"}},[r("router-link",{attrs:{to:"/"}},[t._v("Home")])],1),r("b-nav-item",{attrs:{href:"#"}},[r("router-link",{attrs:{to:"/about"}},[t._v("About")])],1)],1),r("b-navbar-nav",{staticClass:"ul-wrapper"},[r("b-nav-form",{staticClass:"form-wrapper",on:{submit:t.onSearchFormSubmit}},[r("b-form-input",{staticClass:"mr-sm-2 input-text",attrs:{size:"sm",placeholder:"Search"},model:{value:t.searchTerm,callback:function(e){t.searchTerm=e},expression:"searchTerm"}}),r("b-button",{staticClass:"my-2 my-sm-0",attrs:{size:"sm",type:"submit"}},[t._v("Search")])],1)],1)],1)],1)],1)},o=[],c={name:"navbar",data:function(){return{searchTerm:""}},methods:{onSearchFormSubmit:function(t){t.preventDefault(),console.log(this.searchTerm),this.$router.push({name:"results",params:{term:"textSearch?target="+this.searchTerm}})}}},u=c,l=(a("dd27"),a("2877")),f=Object(l["a"])(u,i,o,!1,null,"4f4a58d8",null),d=f.exports,b=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("footer",{staticClass:"footer"},[a("div",{staticClass:"container-fluid"},[a("div",{staticClass:"row"},[t._m(0),a("div",{staticClass:"col-lg-4"},[a("b",[t._v("Quick Links")]),a("div",[a("router-link",{attrs:{to:"/"}},[t._v("Home")]),a("br"),a("router-link",{attrs:{to:"/about"}},[t._v("About")])],1)]),t._m(1)])])])},p=[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"col-lg-4"},[r("img",{staticStyle:{width:"50px"},attrs:{src:a("cf05"),alt:""}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"col-lg-4"},[a("b",[t._v("License")]),a("p",[t._v("\n          Beer information obtained from "),a("u",[a("a",{attrs:{href:"https://catalog.beer"}},[t._v("catalog.beer")])]),t._v(" under\n          "),a("u",[a("a",{attrs:{href:"https://creativecommons.org/licenses/by/4.0"}},[t._v("CC BY 4.0")])]),t._v(" license\n          (excluding all beer and brewer names and brands, which belong to their respective owners).\n        ")])])}],m={name:"myFooter"},v=m,h=(a("5ffb"),Object(l["a"])(v,b,p,!1,null,"239c3277",null)),_=h.exports,g={name:"home",components:{navbar:d,myFooter:_}},y=g,w=(a("034f"),Object(l["a"])(y,n,s,!1,null,null,null)),C=w.exports,x=a("8c4f"),k=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home container-fluid"},[a("typesList"),a("div",{staticClass:"row"},[a("featuredBeer"),a("attributesList")],1)],1)},S=[],j=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"col-lg-8"},[a("h3",[t._v("Featured Beer")]),a("div",{staticClass:"wrapper"},[a("div",{staticClass:"row"},[a("div",{staticClass:"col-lg-4 title-wrapper"},[a("h2",[t._v(t._s(this.data.beerName))])]),a("div",{staticClass:"col-lg-8 main-img-wrapper"},[a("img",{attrs:{src:t.dataImage,alt:""}})])]),a("div",{staticClass:"text-wrapper"},[a("p",{staticClass:"text-left"},[t._v(t._s(this.data.description))])]),a("div",{staticClass:"text-wrapper text-left"},[a("h3",[t._v("Reviews")]),t._l(t.data.staffReviews,function(e){return a("div",[a("p",[t._v(t._s(e.review))]),a("p",[t._v("~"+t._s(e.staffMember))])])})],2),a("div",[a("b-button",{staticClass:"float-left",attrs:{size:"sm",variant:"info"},on:{click:t.onBeerClicked}},[t._v("Show")])],1)])])},$=[],O=a("795b"),B=a.n(O),T=a("bc3a"),E=a.n(T),I="http://somewhere.beer/WebAccess/api/",L=E.a.create({baseURL:I});L.interceptors.request.use(function(t){return t},function(t){return B.a.reject(t)}),L.interceptors.response.use(function(t){return t},function(t){return B.a.reject(t)});var V={name:"featuredBeer",data:function(){return{data:{},dataImage:""}},created:function(){var t=this;L.get("/featured").then(function(e){t.data=e.data,t.dataImage=e.data.images[0]}).catch(function(t){console.log(t)})},methods:{onBeerClicked:function(){this.$router.push({name:"beer",params:{id:this.data.beerId}})}}},A=V,F=(a("a427"),Object(l["a"])(A,j,$,!1,null,"3792a521",null)),P=F.exports,R=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"row wrapper"},[a("div",{staticClass:"col-lg"},t._l(t.types,function(e,r){return a("span",{staticClass:"typeList",on:{click:function(e){return t.onTypeClicked(r)}}},[a("span",[t._v(t._s(e))])])}),0)])},N=[],M={name:"typesList",data:function(){return{types:{ale:"Ale",lager:"Lager",hybrid:"Hybrid",barleywine:"Barleywine",cider:"Cider",amber:"Amber"}}},methods:{onTypeClicked:function(t){this.$router.push({name:"category",params:{type:t}})}}},U=M,z=(a("df1a"),Object(l["a"])(U,R,N,!1,null,"31f0da54",null)),H=z.exports,W=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"col-lg-4"},[a("div",{staticClass:"col-lg-12"},[a("h2",[t._v("Search by attribute")]),a("b-form",{on:{submit:t.onAttributeFormSubmit}},[a("b-form-group",{attrs:{id:"input-group-1",label:"Type","label-for":"attr-type"}},[a("b-form-input",{attrs:{id:"att-type",type:"text"},model:{value:t.type,callback:function(e){t.type=e},expression:"type"}})],1),a("b-form-group",{attrs:{id:"input-group-1",label:"ABV","label-for":"attr-abv"}},[a("b-form-input",{attrs:{id:"att-abv",type:"number",min:"0",max:"100",step:"0.5"},model:{value:t.abvValue,callback:function(e){t.abvValue=e},expression:"abvValue"}})],1),a("b-form-group",{attrs:{id:"input-group-1",label:"IBU","label-for":"attr-ibn"}},[a("b-form-input",{attrs:{id:"att-ibn",type:"number",min:"0",max:"100",step:"0.5"},model:{value:t.ibuValue,callback:function(e){t.ibuValue=e},expression:"ibuValue"}})],1),a("b-form-group",{attrs:{id:"input-group-1",label:"Brewery","label-for":"brower"}},[a("b-form-input",{attrs:{id:"brower",type:"text"},model:{value:t.brewery,callback:function(e){t.brewery=e},expression:"brewery"}})],1),a("b-button",{staticClass:"float-right",attrs:{type:"submit"}},[t._v("Search")])],1)],1)])},q=[],D={name:"attributesList",data:function(){return{abvValue:"",ibuValue:"",brewery:"",type:""}},methods:{onAttributeFormSubmit:function(t){t.preventDefault(),this.$router.push({name:"results",params:{term:"abv="+this.abvValue+"&ibu="+this.ibuValue+"&brewery="+this.brewery+"&type="+this.type}})}}},J=D,Q=(a("501f"),Object(l["a"])(J,W,q,!1,null,"216bf280",null)),Y=Q.exports,G={name:"home",components:{featuredBeer:P,typesList:H,attributesList:Y}},K=G,X=(a("a3cc"),Object(l["a"])(K,k,S,!1,null,null,null)),Z=X.exports,tt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container-fluid"},[a("h1",[t._v(t._s(t.data.category))]),a("h3",[t._v("We have found "+t._s(t.data.numberFound)+" results")]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-lg-12 text-left"},[a("ul",{staticClass:"list-unstyled"},t._l(t.data.beers,function(e){return a("li",[a("router-link",{key:e.beerId,attrs:{to:{name:"beer",params:{id:e.beerId}}}},[t._v(t._s(e.beerName))])],1)}),0)])])])},et=[],at={name:"home",data:function(){return{categoryName:"",data:{}}},created:function(){var t=this;this.categoryName=this.$route.params.type,L.get("category?category="+this.categoryName).then(function(e){t.data=e.data}).catch(function(t){console.log(t)})},components:{}},rt=at,nt=(a("2c9a"),Object(l["a"])(rt,tt,et,!1,null,"48dc667a",null)),st=nt.exports,it=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container-fluid"},[a("h1",[t._v(t._s(t.beer.beerName))]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-lg-8 text-left"},[t.beer.images?a("img",{attrs:{src:t.beerImage,alt:""}}):t._e()]),a("div",{staticClass:"col-lg-4"},[a("h2",[t._v("Attributes List")]),a("p",[t._v("ABV: "+t._s(t.beer.ABV))]),a("p",[t._v("IBU: "+t._s(t.beer.IBU))]),a("p",[t._v("Brewery: "+t._s(t.beer.brewery))]),a("p",[t._v("Style: "+t._s(t.beer.beerStyle))])])]),a("div",{staticClass:"text-wrapper text-left"},[t.beer.description?a("p",[t._v(t._s(t.beer.description))]):t._e(),t.beer.staffReviews?a("h3",[t._v("Reviews")]):t._e(),t._l(t.beer.staffReviews,function(e){return a("div",[a("p",[t._v(t._s(e.review))]),a("p",[t._v("~"+t._s(e.staffMember))])])})],2)])},ot=[],ct={name:"beer",props:["id"],data:function(){return{beer:{},beerImage:""}},created:function(){var t=this,e=this.$route.params.id;L.get("/beer?id="+e).then(function(e){t.beer=e.data,t.beerImage=e.data.images[0]}).catch(function(t){console.log(t)})},components:{}},ut=ct,lt=Object(l["a"])(ut,it,ot,!1,null,"dcda4ba8",null),ft=lt.exports,dt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container-fluid"},[a("h1",[t._v("Results")]),a("h3",[t._v("We have found "+t._s(t.data.numberFound)+" results")]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-lg-12 text-left"},[a("ul",{staticClass:"list-unstyled"},t._l(t.data.beers,function(e){return a("li",[a("router-link",{key:e.beerId,attrs:{to:{name:"beer",params:{id:e.beerId}}}},[t._v(t._s(e.beerName))])],1)}),0)])])])},bt=[],pt={name:"attributesResults",data:function(){return{searchTerm:"",data:{}}},created:function(){var t=this;this.searchTerm=this.$route.params.term,L.get("/attribueSearch?"+this.searchTerm).then(function(e){t.data=e.data}).catch(function(t){console.log(t)})},components:{}},mt=pt,vt=(a("c1c7"),Object(l["a"])(mt,dt,bt,!1,null,"fc7ae578",null)),ht=vt.exports;r["default"].use(x["a"]);var _t=new x["a"]({mode:"history",base:"/",routes:[{path:"/",name:"home",component:Z},{path:"/about",name:"about",component:function(){return a.e("about").then(a.bind(null,"f820"))}},{path:"/category/:type",name:"category",component:st},{path:"/beer/:id",name:"beer",component:ft,props:!0},{path:"/results/:term",name:"results",component:ht}]}),gt=a("9f7b"),yt=a.n(gt);a("f9e3"),a("2dd8");r["default"].use(yt.a),r["default"].config.productionTip=!1,new r["default"]({router:_t,render:function(t){return t(C)}}).$mount("#app")},"5ffb":function(t,e,a){"use strict";var r=a("18a8"),n=a.n(r);n.a},"64a9":function(t,e,a){},a108:function(t,e,a){},a3cc:function(t,e,a){"use strict";var r=a("df06"),n=a.n(r);n.a},a427:function(t,e,a){"use strict";var r=a("2ec4"),n=a.n(r);n.a},c119:function(t,e,a){},c1c7:function(t,e,a){"use strict";var r=a("a108"),n=a.n(r);n.a},cf05:function(t,e,a){t.exports=a.p+"img/logo.17625ab7.png"},dd27:function(t,e,a){"use strict";var r=a("e989"),n=a.n(r);n.a},df06:function(t,e,a){},df1a:function(t,e,a){"use strict";var r=a("c119"),n=a.n(r);n.a},e989:function(t,e,a){},fd48:function(t,e,a){}});
//# sourceMappingURL=app.debe8656.js.map