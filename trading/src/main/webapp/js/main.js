const continuousPromise = (promiseFactory, interval)  => {
    const execute = () => promiseFactory().finally(waitAndExecute);
    const waitAndExecute = () => window.setTimeout(execute, interval);
    execute();
}
