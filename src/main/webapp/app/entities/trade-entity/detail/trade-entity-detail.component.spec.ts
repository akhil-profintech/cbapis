import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TradeEntityDetailComponent } from './trade-entity-detail.component';

describe('TradeEntity Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TradeEntityDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TradeEntityDetailComponent,
              resolve: { tradeEntity: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TradeEntityDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load tradeEntity on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TradeEntityDetailComponent);

      // THEN
      expect(instance.tradeEntity).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
