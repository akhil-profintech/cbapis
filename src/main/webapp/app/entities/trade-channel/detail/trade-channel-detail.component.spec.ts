import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TradeChannelDetailComponent } from './trade-channel-detail.component';

describe('TradeChannel Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TradeChannelDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TradeChannelDetailComponent,
              resolve: { tradeChannel: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TradeChannelDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load tradeChannel on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TradeChannelDetailComponent);

      // THEN
      expect(instance.tradeChannel).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
