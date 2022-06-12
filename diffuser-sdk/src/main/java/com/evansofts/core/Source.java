package com.evansofts.core;

public abstract class Source extends BaseComponent {}

// GeneratorSource -> loop
// EventSource -> web-socket/http event
// AbstractTransformer (vrl)
// AbstractSink (aggregation, s3-archiving)

// Only source buffer