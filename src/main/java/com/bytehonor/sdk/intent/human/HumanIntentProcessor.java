package com.bytehonor.sdk.intent.human;

public final class HumanIntentProcessor {

//    private static final Logger LOG = LoggerFactory.getLogger(HumanIntentProcessor.class);
//
//    public static IntentResult process(IntentRequest request) {
//        IntentFilterProcessor.before(request);
//        IntentResult result = recognize(request);
//        IntentFilterProcessor.after(result);
//        return result;
//    }
//
//    /**
//     * 必有值返回
//     * 
//     * @param request
//     * @return
//     */
//    public static IntentResult recognize(IntentRequest request) {
//        Objects.requireNonNull(request, "request");
//        Objects.requireNonNull(request.getSession(), "session");
//        IntentTarget target = null;
//        try {
//            target = doRecognize(request);
//        } catch (Exception e) {
//            LOG.error("query:{}, uuid:{}, app:{}, error", request.getQuery(), request.getUuid(), request.getApp(), e);
//        }
//        if (target == null) {
//            target = IntentTarget.undefined(request, request.getApp());
//        }
//        
//        target.getSession().setNowIntent(target.getIntent());
//
//        if (LOG.isInfoEnabled()) {
//            LOG.info("query:{}, intent:{}, recognizer:{}, uuid:{}", request.getQuery(), target.getIntent(),
//                    target.getRecognizer(), request.getUuid());
//        }
//
//        LOG.info("session id:{}, preIntent:{}, nowIntent:{}", target.getSession().getId(),
//                target.getSession().getPreIntent(), target.getSession().getNowIntent());
//
//        if (target.getSession().isAuto() == false) {
//            // 停止自动应答
//            return IntentResult.non(target);
//        }
//
//        if (IntentConstants.PUBLIC_AMBIGUOUS.equals(target.getIntent())) {
//            // 含糊不清的
//            return doAmbiguous(target);
//        }
//
//        IntentResolver handler = IntentResolverFactory.optional(target.getIntent());
//        if (handler == null) {
//            LOG.error("intent:{}, query:{}, uuid:{}, app:{} no handler", target.getIntent(), target.getQuery(),
//                    target.getSession().getUuid(), target.getSession().getApp());
//            return IntentResult.text(target, IntentConstants.TIP_HANDLER_NON);
//        }
//        IntentResult result = null;
//        try {
//            result = handler.resolve(target);
//        } catch (Exception e) {
//            LOG.error("query:{}, intent:{}, recognizer:{}, error", target.getQuery(), target.getIntent(),
//                    target.getRecognizer(), e);
//        }
//        if (result == null) {
//            result = IntentResult.text(target, IntentConstants.TIP_HANDLER_ERROR);
//        }
//        return result;
//    }
//
//    private static IntentResult doAmbiguous(IntentTarget target) {
//        List<String> intents = StringSplitUtils.split(target.getSlotValue("intents"));
//        List<String> patterns = IntentRecognizeProcessor.listPatterns(target.getSession().getApp(), intents);
//        StringBuilder sb = new StringBuilder();
//        sb.append(IntentConstants.TIP_HANDLER_AMBIGUOUS).append("\r\n");
//        for (String pattern : patterns) {
//            sb.append("\r\n");
//            sb.append(pattern).append("?");
//        }
//        return IntentResult.text(target, sb.toString());
//    }
//
//    /**
//     * 必有值返回
//     * 
//     * @param request
//     * @return
//     */
//    private static IntentTarget doRecognize(IntentRequest request) {
//        Objects.requireNonNull(request, "request");
//        long now = System.currentTimeMillis();
//        if (request.getSession().isAuto() == false && (now - request.getSession().getPreTime() < TimeConstants.HOUR)) {
//            return IntentTarget.manual(request);
//        }
//
//        if (SpringString.isEmpty(request.getQuery())) {
//            LOG.warn("query is empty, uuid:{}, app:{}", request.getUuid(), request.getApp());
//            return IntentTarget.undefined(request, request.getApp());
//        }
//
//        List<IntentRecognizer> recognizers = IntentRecognizerFactory.list(request.getApp());
//        if (CollectionUtils.isEmpty(recognizers)) {
//            LOG.warn("query:{}, app:{} no recognizers", request.getQuery(), request.getApp());
//            return IntentTarget.undefined(request, request.getApp());
//        }
//        List<IntentTarget> list = Collections.synchronizedList(new ArrayList<IntentTarget>());
//        recognizers.parallelStream().forEach(recognizer -> {
//            IntentTarget tar = recognizer.recoginze(request);
//            if (tar.getScore() > 50) {
//                LOG.info("{}, {}, {}", tar.getScore(), tar.getIntent(), tar.getRecognizer());
//                list.add(tar);
//            }
//        });
//        int size = list.size();
//        if (size == 1) {
//            return list.get(0);
//        }
//        if (size > 1) {
//            // 多个目标,模糊不清也是一个目标,进行二次提问
//            return IntentTarget.ambiguous(request, list);
//        }
//        // 缺省的时候就放app，由应用的默认处理器去处理
//        return IntentTarget.undefined(request, request.getApp());
//    }
//
//    public static void setRecognizer(IntentRecognizer recognizer) {
//        if (recognizer == null) {
//            return;
//        }
//        IntentRecognizerFactory.put(recognizer);
//    }
//
//    public static void setResolver(IntentResolver resolver) {
//        if (resolver == null) {
//            return;
//        }
//        IntentResolverFactory.put(resolver);
//    }
//
//    public static void setFilter(IntentRequestFilter filter) {
//        if (filter == null) {
//            return;
//        }
//        IntentFilterFactory.putRequest(filter);
//    }
//
//    public static void setFilter(IntentResultFilter filter) {
//        if (filter == null) {
//            return;
//        }
//        IntentFilterFactory.putResult(filter);
//    }
}
