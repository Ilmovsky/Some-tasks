package com.alex.problem.domain.usecase;

interface UseCase <P extends UseCase.RequestValues, R> {

    R execute(P params);

    /**
     * Data passed to a request.
     */
    interface RequestValues {
    }
}
