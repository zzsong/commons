package com.zss.commons.support.math;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MathUtils {

	// 判断奇数
	public static boolean isOdd(int num) {
		return (num & 1) == 1;
	}

	// 判断偶数，0是偶数
	public static boolean isEven(int num) {
		return (num & 1) == 0;
	}
	
	/**
	 * 区间数相加	
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int sum(int begin, int end) {
		while (end != 0) {
            int _a = begin ^ end;
            int _b = (begin & end) << 1;
            begin = _a;
            end = _b;
        }
        return begin;
	}
	
	//扩容2倍
	public static int capacity2X(int num) {
		return num << 1;
	}

	/**
	 * 组合(去重)
	 * @param arrays
	 * @param size
	 * @param symbol
	 * @return
	 */
	public static List<String> combinations(Set<String> arrays, int size, String symbol){
		if (arrays.size()<size){
			throw new IllegalArgumentException("参数异常");
		}
		symbol=symbol==null?"":symbol;
		Set<Set<String>> result = Sets.combinations(arrays, size);
		String finalSymbol = symbol;
		return result.stream().map(o->StringUtils.join(o, finalSymbol)).collect(Collectors.toList());
	}

	/**
	 * 排列 list
	 * @param arrays
	 */
	public static List<List<String>> orderedPermutations(List<String> arrays){
		return Collections2.orderedPermutations(arrays).stream().collect(Collectors.toList());
	}

	/**
	 * 排列总数
	 * @param n
	 * @return
	 */
	public static long factorialCount(int n){
		return CombinatoricsUtils.factorial(n);
	}

	/**
	 * 组合数
	 * @param n 总数
	 * @param k	组合数
	 * @return
	 */
	public static long combinationsCount(int n, int k){
		return CombinatoricsUtils.binomialCoefficient(n,k);
	}

	/**
	 * 笛卡尔积
	 * @param symbol
	 * @param sets
	 * @param <B>
	 * @return 按symbol连接符的字符串
	 */
	public static <B> List<String> cartesianProduct(String symbol, Set<? extends B>... sets){
		Set<List<B>> set = Sets.cartesianProduct(sets);
		List<String> list = Lists.newArrayList();
		for (List<B> bs : set) {
			list.add(join(bs, symbol));
		}
		return list;
	}
	/**
	 * 过滤掉分隔字条串=空字符串
	 * @param sequence
	 * @param symbol
	 * @return
	 */
	public static List<String> splitToList(String sequence, String symbol){
		return Splitter.on(symbol).trimResults().omitEmptyStrings().splitToList(sequence);
	}

	/**
	 * 过滤掉null对象值，进行symbol拼接
	 * @param list
	 * @param symbol
	 * @param <B>
	 * @return
	 */
	public static <B>  String join(List<? extends B> list, String symbol){
		return Joiner.on(symbol).skipNulls().join(list);
	}
	
	public static void main(String[] args) {
		int max = 200000;
		Set<String> a = Sets.newHashSet( Arrays.asList("a","b","c"));
		Set<String> b = Sets.newHashSet( Arrays.asList("a","b"));
		Set<String> c = Sets.newHashSet( Arrays.asList("b","c","d"));

		List<String> list = cartesianProduct(",",a,b,c);
		System.out.println("=========>>"+list.size());
		list.forEach(s-> System.out.println(s));
		//处理组合排列
		//<dependency>
		//    <groupId>com.github.dpaukov</groupId>
		//    <artifactId>combinatoricslib3</artifactId>
		//    <version>3.3.0</version>
		//</dependency>
	}
}
