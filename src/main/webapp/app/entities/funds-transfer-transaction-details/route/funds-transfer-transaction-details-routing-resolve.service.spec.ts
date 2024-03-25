import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';

import fundsTransferTransactionDetailsResolve from './funds-transfer-transaction-details-routing-resolve.service';

describe('FundsTransferTransactionDetails routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: FundsTransferTransactionDetailsService;
  let resultFundsTransferTransactionDetails: IFundsTransferTransactionDetails | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(FundsTransferTransactionDetailsService);
    resultFundsTransferTransactionDetails = undefined;
  });

  describe('resolve', () => {
    it('should return IFundsTransferTransactionDetails returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        fundsTransferTransactionDetailsResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFundsTransferTransactionDetails = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFundsTransferTransactionDetails).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        fundsTransferTransactionDetailsResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFundsTransferTransactionDetails = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFundsTransferTransactionDetails).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFundsTransferTransactionDetails>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        fundsTransferTransactionDetailsResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFundsTransferTransactionDetails = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFundsTransferTransactionDetails).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
