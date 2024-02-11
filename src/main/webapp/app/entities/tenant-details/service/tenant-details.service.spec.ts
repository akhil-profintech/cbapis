import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITenantDetails } from '../tenant-details.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../tenant-details.test-samples';

import { TenantDetailsService } from './tenant-details.service';

const requireRestSample: ITenantDetails = {
  ...sampleWithRequiredData,
};

describe('TenantDetails Service', () => {
  let service: TenantDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: ITenantDetails | ITenantDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TenantDetailsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a TenantDetails', () => {
      const tenantDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tenantDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TenantDetails', () => {
      const tenantDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tenantDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TenantDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TenantDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TenantDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTenantDetailsToCollectionIfMissing', () => {
      it('should add a TenantDetails to an empty array', () => {
        const tenantDetails: ITenantDetails = sampleWithRequiredData;
        expectedResult = service.addTenantDetailsToCollectionIfMissing([], tenantDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tenantDetails);
      });

      it('should not add a TenantDetails to an array that contains it', () => {
        const tenantDetails: ITenantDetails = sampleWithRequiredData;
        const tenantDetailsCollection: ITenantDetails[] = [
          {
            ...tenantDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTenantDetailsToCollectionIfMissing(tenantDetailsCollection, tenantDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TenantDetails to an array that doesn't contain it", () => {
        const tenantDetails: ITenantDetails = sampleWithRequiredData;
        const tenantDetailsCollection: ITenantDetails[] = [sampleWithPartialData];
        expectedResult = service.addTenantDetailsToCollectionIfMissing(tenantDetailsCollection, tenantDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tenantDetails);
      });

      it('should add only unique TenantDetails to an array', () => {
        const tenantDetailsArray: ITenantDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tenantDetailsCollection: ITenantDetails[] = [sampleWithRequiredData];
        expectedResult = service.addTenantDetailsToCollectionIfMissing(tenantDetailsCollection, ...tenantDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tenantDetails: ITenantDetails = sampleWithRequiredData;
        const tenantDetails2: ITenantDetails = sampleWithPartialData;
        expectedResult = service.addTenantDetailsToCollectionIfMissing([], tenantDetails, tenantDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tenantDetails);
        expect(expectedResult).toContain(tenantDetails2);
      });

      it('should accept null and undefined values', () => {
        const tenantDetails: ITenantDetails = sampleWithRequiredData;
        expectedResult = service.addTenantDetailsToCollectionIfMissing([], null, tenantDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tenantDetails);
      });

      it('should return initial array if no TenantDetails is added', () => {
        const tenantDetailsCollection: ITenantDetails[] = [sampleWithRequiredData];
        expectedResult = service.addTenantDetailsToCollectionIfMissing(tenantDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(tenantDetailsCollection);
      });
    });

    describe('compareTenantDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTenantDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTenantDetails(entity1, entity2);
        const compareResult2 = service.compareTenantDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTenantDetails(entity1, entity2);
        const compareResult2 = service.compareTenantDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTenantDetails(entity1, entity2);
        const compareResult2 = service.compareTenantDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
