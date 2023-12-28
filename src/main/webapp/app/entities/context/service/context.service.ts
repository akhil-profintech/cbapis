import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IContext, NewContext } from '../context.model';

export type PartialUpdateContext = Partial<IContext> & Pick<IContext, 'id'>;

type RestOf<T extends IContext | NewContext> = Omit<T, 'transactionDate'> & {
  transactionDate?: string | null;
};

export type RestContext = RestOf<IContext>;

export type NewRestContext = RestOf<NewContext>;

export type PartialUpdateRestContext = RestOf<PartialUpdateContext>;

export type EntityResponseType = HttpResponse<IContext>;
export type EntityArrayResponseType = HttpResponse<IContext[]>;

@Injectable({ providedIn: 'root' })
export class ContextService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/contexts');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(context: NewContext): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(context);
    return this.http
      .post<RestContext>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(context: IContext): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(context);
    return this.http
      .put<RestContext>(`${this.resourceUrl}/${this.getContextIdentifier(context)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(context: PartialUpdateContext): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(context);
    return this.http
      .patch<RestContext>(`${this.resourceUrl}/${this.getContextIdentifier(context)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestContext>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestContext[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getContextIdentifier(context: Pick<IContext, 'id'>): number {
    return context.id;
  }

  compareContext(o1: Pick<IContext, 'id'> | null, o2: Pick<IContext, 'id'> | null): boolean {
    return o1 && o2 ? this.getContextIdentifier(o1) === this.getContextIdentifier(o2) : o1 === o2;
  }

  addContextToCollectionIfMissing<Type extends Pick<IContext, 'id'>>(
    contextCollection: Type[],
    ...contextsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const contexts: Type[] = contextsToCheck.filter(isPresent);
    if (contexts.length > 0) {
      const contextCollectionIdentifiers = contextCollection.map(contextItem => this.getContextIdentifier(contextItem)!);
      const contextsToAdd = contexts.filter(contextItem => {
        const contextIdentifier = this.getContextIdentifier(contextItem);
        if (contextCollectionIdentifiers.includes(contextIdentifier)) {
          return false;
        }
        contextCollectionIdentifiers.push(contextIdentifier);
        return true;
      });
      return [...contextsToAdd, ...contextCollection];
    }
    return contextCollection;
  }

  protected convertDateFromClient<T extends IContext | NewContext | PartialUpdateContext>(context: T): RestOf<T> {
    return {
      ...context,
      transactionDate: context.transactionDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restContext: RestContext): IContext {
    return {
      ...restContext,
      transactionDate: restContext.transactionDate ? dayjs(restContext.transactionDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestContext>): HttpResponse<IContext> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestContext[]>): HttpResponse<IContext[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
