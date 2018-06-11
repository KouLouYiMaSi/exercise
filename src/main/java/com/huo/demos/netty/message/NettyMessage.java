package com.huo.demos.netty.message;

public final class NettyMessage {

    private Header header;
    private Object body;

    public final Header getHeader() {
        return header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage [header" + header.toString() + "body=" + body.toString() + "]";
    }

}
